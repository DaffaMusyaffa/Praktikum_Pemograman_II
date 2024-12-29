package view;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UserView extends JFrame {
    private JTextField txtName = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JButton btnAdd = new JButton("Add User");
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnExport = new JButton("Export");
    private JList<String> userList = new JList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JProgressBar progressBar = new JProgressBar(0, 100);
    private JLabel statusLabel = new JLabel("Ready", JLabel.CENTER);

    public UserView() {
        setTitle("User Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel untuk input dan tombol
        JPanel topPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 1));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(txtEmail);
        topPanel.add(inputPanel);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnExport);
        topPanel.add(buttonPanel);

        // Progress panel
        JPanel progressPanel = new JPanel(new BorderLayout(5, 5));
        progressBar.setStringPainted(true);
        progressPanel.add(statusLabel, BorderLayout.NORTH);
        progressPanel.add(progressBar, BorderLayout.CENTER);
        topPanel.add(progressPanel);

        // List panel
        userList.setModel(listModel);
        JScrollPane scrollPane = new JScrollPane(userList);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateProgress(int progress) {
        progressBar.setValue(progress);
        progressBar.setString(progress + "%");
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }

    public void disableControlsDuringOperation(boolean disabled) {
        txtName.setEnabled(!disabled);
        txtEmail.setEnabled(!disabled);
        btnAdd.setEnabled(!disabled);
        btnRefresh.setEnabled(!disabled);
        btnExport.setEnabled(!disabled);
        setCursor(disabled ? Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) 
                         : Cursor.getDefaultCursor());
    }

    // Existing methods
    public String getNameInput() { return txtName.getText(); }
    public String getEmailInput() { return txtEmail.getText(); }
    public void clearInputs() {
        txtName.setText("");
        txtEmail.setText("");
    }
    public void setUserList(String[] users) {
        listModel.clear();
        for (String user : users) {
            listModel.addElement(user);
        }
    }
    public void addAddUserListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }
    public void addExportListener(ActionListener listener) {
        btnExport.addActionListener(listener);
    }
}