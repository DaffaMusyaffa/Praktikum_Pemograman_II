import java.awt.*;
import javax.swing.*;

public class Pengeluaran extends JFrame {
    private JTextField descriptionField, amountField;
    private JTextArea outputArea;

    public Pengeluaran() {
        setTitle("Pengeluaran Laundry");
        setSize(400, 250); // Ukuran disesuaikan setelah menghapus komentar
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        panel.add(new JLabel("Deskripsi:"));
        descriptionField = new JTextField();
        panel.add(descriptionField);

        panel.add(new JLabel("Jumlah:"));
        amountField = new JTextField();
        panel.add(amountField);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Tambah Pengeluaran");
        addButton.addActionListener(e -> addPengeluaran());
        buttonPanel.add(addButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());
        buttonPanel.add(backButton);

        panel.add(buttonPanel);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        panel.add(new JScrollPane(outputArea));

        add(panel);
        setVisible(true);
    }

    private void addPengeluaran() {
        String description = descriptionField.getText();
        String amountStr = amountField.getText();
        
        try {
            double amount = Double.parseDouble(amountStr);
            outputArea.append("Pengeluaran Ditambahkan: " + description + ", Jumlah: " + amount + "\n");
            descriptionField.setText("");
            amountField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah tidak valid. Mohon masukkan angka.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Pengeluaran::new);
    }
}
