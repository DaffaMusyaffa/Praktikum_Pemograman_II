import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Transaksi extends JFrame {
    private JTextField customerField;
    private JTextField priceField;
    private JRadioButton serviceWash, serviceDry;
    private JComboBox<String> serviceCombo;
    private DefaultTableModel tableModel;
    private JTable table;

    public Transaksi() {
        setTitle("Transaksi Laundry");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2));

        inputPanel.add(new JLabel("Nama Pelanggan:"));
        customerField = new JTextField();
        inputPanel.add(customerField);

        inputPanel.add(new JLabel("Harga:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Layanan:"));

        // Panel untuk radio button
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        serviceWash = new JRadioButton("Cuci");
        serviceDry = new JRadioButton("Kering");
        ButtonGroup group = new ButtonGroup();
        group.add(serviceWash);
        group.add(serviceDry);
        
        radioPanel.add(serviceWash);
        radioPanel.add(serviceDry);
        
        inputPanel.add(radioPanel);

        // Panel untuk "Pilih Layanan"
        JPanel servicePanel = new JPanel();
        servicePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        servicePanel.add(new JLabel("Pilih Layanan:"));
        serviceCombo = new JComboBox<>(new String[]{"Cuci Kering", "Cuci Basah", "Setrika"});
        servicePanel.add(serviceCombo);
        inputPanel.add(servicePanel);

        // Panel untuk tabel
        tableModel = new DefaultTableModel(new String[]{"Pelanggan", "Layanan", "Harga"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        
        // Menambahkan input panel dan tabel ke panel utama
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());
        buttonPanel.add(backButton);

        JButton addButton = new JButton("Tambah Transaksi");
        addButton.addActionListener(e -> addTransaction());
        buttonPanel.add(addButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void addTransaction() {
        String customerName = customerField.getText();
        double price;

        try {
            price = Double.parseDouble(priceField.getText());

            String serviceType = serviceWash.isSelected() ? "Cuci" : "Kering";
            String selectedService = (String) serviceCombo.getSelectedItem();

            tableModel.addRow(new Object[]{customerName, selectedService, price});

            // Reset input fields
            customerField.setText("");
            priceField.setText("");
            serviceWash.setSelected(false);
            serviceDry.setSelected(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga tidak valid. Mohon masukkan angka.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
