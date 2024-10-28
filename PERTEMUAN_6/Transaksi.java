import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Transaksi extends JFrame {
    private JTextField customerField;
    private JTextField priceField;
    private JRadioButton serviceWash, serviceDry;
    private JComboBox<String> serviceCombo;
    private JSlider discountSlider;
    private JSpinner quantitySpinner;
    private DefaultTableModel tableModel;
    private JTable table;

    public Transaksi() {
        setTitle("Transaksi Laundry");
        setSize(600, 450); // Ukuran disesuaikan setelah menghapus komentar
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

        inputPanel.add(new JLabel("Jumlah:"));
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        inputPanel.add(quantitySpinner);

        inputPanel.add(new JLabel("Diskon (%):"));
        discountSlider = new JSlider(0, 100, 0);
        discountSlider.setMajorTickSpacing(20);
        discountSlider.setMinorTickSpacing(5);
        discountSlider.setPaintTicks(true);
        discountSlider.setPaintLabels(true);
        inputPanel.add(discountSlider);

        inputPanel.add(new JLabel("Layanan:"));
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

        inputPanel.add(new JLabel("Pilih Layanan:"));
        serviceCombo = new JComboBox<>(new String[]{"Cuci Kering", "Cuci Basah", "Setrika"});
        inputPanel.add(serviceCombo);

        // Panel untuk tabel
        tableModel = new DefaultTableModel(new String[]{"Pelanggan", "Layanan", "Harga", "Jumlah", "Diskon (%)", "Total"}, 0);
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
            int quantity = (Integer) quantitySpinner.getValue();
            int discount = discountSlider.getValue();
            double total = (price * quantity) - ((price * quantity) * discount / 100.0);

            String serviceType = serviceWash.isSelected() ? "Cuci" : "Kering";
            String selectedService = (String) serviceCombo.getSelectedItem();

            tableModel.addRow(new Object[]{customerName, selectedService, price, quantity, discount, String.format("%.2f", total)});

            // Reset input fields
            customerField.setText("");
            priceField.setText("");
            quantitySpinner.setValue(1);
            discountSlider.setValue(0);
            serviceWash.setSelected(false);
            serviceDry.setSelected(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga tidak valid. Mohon masukkan angka.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Transaksi::new);
    }
}
