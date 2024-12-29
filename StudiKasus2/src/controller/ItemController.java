package controller;

import org.apache.ibatis.session.SqlSession;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Item;
import model.ItemMapper;
import model.MyBatisUtil;
import view.ItemView;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ItemController {

    private final ItemView itemView;
    private ItemMapper itemMapper;

    public ItemController(ItemView itemView) {
        this.itemView = itemView;

        // Add listeners to the view
        itemView.addAddItemListener(new AddItemListener());
        itemView.addUpdateItemListener(new UpdateItemListener());
        itemView.addDeleteItemListener(new DeleteItemListener());
        itemView.addRefreshButtonListener(new RefreshButtonListener());
        itemView.addTableSelectionListener(new TableSelectionListener());

        refreshItemTable(); // Initial load, refresh data table
    }

    // Method to refresh data table
    private void refreshItemTable() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            itemMapper = session.getMapper(ItemMapper.class);
            if (itemMapper != null) {
                List<Item> items = itemMapper.getAllItems();
                itemView.updateItemTable(items);
            }
            itemView.clearInputFields(); // Clear input form
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(itemView, "Error loading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to validate and extract input data
    private Item extractInputData() throws Exception {
        String name = itemView.getNameField().getText();
        String quantityText = itemView.getQuantityField().getText();
        String location = itemView.getLocationField().getText();
        String priceText = itemView.getPriceField().getText();
        String category = itemView.getCategoryField().getText();

        // Validate empty fields
        if (name.isEmpty() || location.isEmpty() || category.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
            throw new Exception("Please fill in all fields.");
        }

        int quantity;
        BigDecimal price;

        try {
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException ex) {
            throw new Exception("Quantity must be a valid integer.");
        }

        try {
            price = new BigDecimal(priceText);
        } catch (NumberFormatException ex) {
            throw new Exception("Price must be a valid number.");
        }

        return new Item(name, quantity, location, price, category);
    }

    // Listener for adding item
    class AddItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Item item = extractInputData();

                // Save item to database
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    itemMapper = session.getMapper(ItemMapper.class);
                    if (itemMapper != null) {
                        itemMapper.addItem(item);
                        session.commit();
                        refreshItemTable();
                        JOptionPane.showMessageDialog(itemView, "Item added successfully!");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(itemView, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Listener for updating item
    class UpdateItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = itemView.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                try {
                    int id = (int) itemView.getTable().getValueAt(selectedRow, 0);
                    Item item = extractInputData();
                    item.setId(id);

                    try (SqlSession session = MyBatisUtil.getSqlSession()) {
                        itemMapper = session.getMapper(ItemMapper.class);
                        if (itemMapper != null) {
                            itemMapper.updateItem(item);
                            session.commit();
                            refreshItemTable();
                            JOptionPane.showMessageDialog(itemView, "Item updated successfully!");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(itemView, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(itemView, "Please select an item to update.");
            }
        }
    }

    // Listener for deleting item
    class DeleteItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = itemView.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) itemView.getTable().getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(itemView, "Delete this item?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try (SqlSession session = MyBatisUtil.getSqlSession()) {
                        itemMapper = session.getMapper(ItemMapper.class);
                        if (itemMapper != null) {
                            itemMapper.deleteItem(id);
                            session.commit();
                            refreshItemTable();
                            JOptionPane.showMessageDialog(itemView, "Item deleted successfully!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(itemView, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(itemView, "Please select an item to delete.");
            }
        }
    }

    // Listener for refresh button
    class RefreshButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshItemTable();
        }
    }

    // Listener for table selection
    public class TableSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = itemView.getTable().getSelectedRow();
                if (selectedRow >= 0) {
                    itemView.getNameField().setText(itemView.getTable().getValueAt(selectedRow, 1).toString());
                    itemView.getQuantityField().setText(itemView.getTable().getValueAt(selectedRow, 2).toString());
                    itemView.getLocationField().setText(itemView.getTable().getValueAt(selectedRow, 3).toString());
                    itemView.getPriceField().setText(itemView.getTable().getValueAt(selectedRow, 4).toString());
                    itemView.getCategoryField().setText(itemView.getTable().getValueAt(selectedRow, 5).toString());
                }
            }
        }
    }
}
