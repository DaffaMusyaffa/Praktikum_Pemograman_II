package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import model.Item;

public class ItemView extends JFrame {

    private JTable table;
    private JTextField nameField, quantityField, locationField, priceField, categoryField;
    private JButton addButton, updateButton, deleteButton, refreshButton;

    public ItemView() {
        // Initialize components
        setTitle("Item Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10));

        // Create table
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Name", "Quantity", "Location", "Price", "Category"}
        ));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for input form
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        formPanel.add(quantityField);

        formPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        formPanel.add(locationField);

        formPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        formPanel.add(priceField);

        formPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        formPanel.add(categoryField);

        add(formPanel, BorderLayout.NORTH);

        // Action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = new JButton("Add Item");
        updateButton = new JButton("Update Item");
        deleteButton = new JButton("Delete Item");
        refreshButton = new JButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Getters for input fields
    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public JTextField getLocationField() {
        return locationField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getCategoryField() {
        return categoryField;
    }

    public JTable getTable() {
        return table;
    }

    // Add listeners for buttons
    public void addAddItemListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addUpdateItemListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addDeleteItemListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public void addTableSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }

    // Update item table
    public void updateItemTable(List<Item> items) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear old data
        for (Item item : items) {
            model.addRow(new Object[]{
                    item.getId(), item.getName(), item.getQuantity(),
                    item.getLocation(), item.getPrice(), item.getCategory()
            });
        }
    }

    // Clear input fields
    public void clearInputFields() {
        nameField.setText("");
        quantityField.setText("");
        locationField.setText("");
        priceField.setText("");
        categoryField.setText("");
    }
}
