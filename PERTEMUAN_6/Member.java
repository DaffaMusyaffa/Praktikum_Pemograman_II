import java.awt.*;
import javax.swing.*;

public class Member extends JFrame {
    private JTextField nameField, phoneField, addressField;
    private JTextArea outputArea;

    public Member() {
        setTitle("Member Laundry");
        setSize(400, 250); // Ukuran disesuaikan setelah menghapus komentar
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        panel.add(new JLabel("Nama Member:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Nomor Telepon:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Alamat:"));
        addressField = new JTextField();
        panel.add(addressField);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Tambah Member");
        addButton.addActionListener(e -> addMember());
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

    private void addMember() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        if (!name.isEmpty() && !phone.isEmpty() && !address.isEmpty()) {
            outputArea.append("Member Ditambahkan: " + name + ", Telepon: " + phone + ", Alamat: " + address + "\n");
            nameField.setText("");
            phoneField.setText("");
            addressField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Member::new);
    }
}
