package PERTEMUAN_2.Latihan_7_Mod;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LATIHAN_7_MOD extends JFrame implements ActionListener, ItemListener, ChangeListener {

    private JTextField kotakNama;
    private JTextField kotakNoHp;
    private JButton tombolSimpan;
    private JButton tombolReset;
    private JTextArea areaBiodata;
    private JCheckBox checkBox;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private ButtonGroup bg;
    private boolean isCheckBoxSelected;
    private JList<String> listJenisTabungan;
    private DefaultListModel<String> listModel;
    private JMenuBar menuBar;
    private JSlider slider;
    private JSpinner spinner;

    public LATIHAN_7_MOD() {
        this.setTitle("FORM PENDAFTARAN NASABAH BANK");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 750);
        this.setLayout(null);

        // Menu
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        // Field Nama
        JLabel LabelNama = new JLabel("Nama :");
        LabelNama.setBounds(15, 20, 100, 30);
        kotakNama = new JTextField();
        kotakNama.setBounds(85, 20, 200, 30);

        // Field No HP
        JLabel LabelNoHp = new JLabel("Nomor Hp :");
        LabelNoHp.setBounds(15, 60, 100, 30);
        kotakNoHp = new JTextField();
        kotakNoHp.setBounds(85, 60, 200, 30);

        // JSpinner Tanggal Lahir
        JLabel spinnerLabel = new JLabel("Tanggal Lahir : ");
        spinnerLabel.setBounds(15, 100, 350, 30);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1990);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        SpinnerDateModel model = new SpinnerDateModel(calendar.getTime(), null, null, calendar.DAY_OF_MONTH);
        spinner = new JSpinner(model);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, "dd-MM-yyyy");
        spinner.setEditor(dateEditor);
        spinner.setBounds(15, 130, 350, 30);

        // Radio Jenis Kelamin
        JLabel jenisKelamin = new JLabel("Jenis Kelamin : ");
        jenisKelamin.setBounds(15, 170, 100, 30);
        radio1 = new JRadioButton("Laki-Laki");
        radio1.setBounds(15, 195, 350, 30);
        radio2 = new JRadioButton("Perempuan");
        radio2.setBounds(15, 225, 350, 30);
        bg = new ButtonGroup();
        bg.add(radio1);
        bg.add(radio2);

        // Checkbox
        checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(15, 250, 350, 30);
        checkBox.addItemListener(this);

        // JList Tabungan
        JLabel jenisTabungan = new JLabel("Jenis Tabungan : ");
        jenisTabungan.setBounds(15, 280, 100, 30);
        listModel = new DefaultListModel<>();
        listModel.addElement("Tabungan Rumah");
        listModel.addElement("Tabungan Investasi");
        listModel.addElement("Tabungan Pendidikan");
        listModel.addElement("Tabungan Haji");
        listJenisTabungan = new JList<>(listModel);
        listJenisTabungan.setBounds(15, 306, 200, 75);
        listJenisTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Button Simpan
        tombolSimpan = new JButton("Simpan");
        tombolSimpan.setBounds(15, 625, 100, 30);
        tombolSimpan.addActionListener(this);

        // Button Reset
        tombolReset = new JButton("Reset");
        tombolReset.setBounds(150, 625, 100, 30);
        tombolReset.addActionListener(this);

        // JTextArea field menampung nilai
        areaBiodata = new JTextArea("");
        areaBiodata.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaBiodata);
        scrollPane.setBounds(15, 515, 280, 100);

        // JSlider
        JLabel sliderLabel = new JLabel("Frekuensi transaksi per bulan : ");
        sliderLabel.setBounds(15, 385, 500, 30);
        slider = new JSlider(0, 100, 0);
        slider.setBounds(15, 425, 380, 75);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);


        

        menuBar.add(fileMenu);
        fileMenu.add(exitMenuItem);
        this.setJMenuBar(menuBar);

        exitMenuItem.addActionListener(e -> System.exit(0));

        add(LabelNama);
        add(kotakNama);
        add(LabelNoHp);
        add(kotakNoHp);
        add(spinnerLabel);
        add(spinner);
        add(jenisKelamin);
        add(radio1);
        add(radio2);
        add(checkBox);
        add(jenisTabungan);
        add(listJenisTabungan);
        add(sliderLabel);
        add(slider);
        add(tombolSimpan);
        add(tombolReset);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        isCheckBoxSelected = e.getStateChange() == ItemEvent.SELECTED;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tombolSimpan) {
            JPasswordField passwordField = new JPasswordField();
            int masukkanPassword = JOptionPane.showConfirmDialog(
                    this,
                    passwordField,
                    "Masukkan Password Anda,",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (masukkanPassword == JOptionPane.OK_OPTION) {
                char[] password = passwordField.getPassword();
                String passwordString = new String(password);

                if (passwordString.equals("P@ssw0rd")) {
                    String nama = kotakNama.getText();
                    String noHp = kotakNoHp.getText();
                    Date tanggalLahir = (Date) spinner.getValue();
                    String tanggal = new SimpleDateFormat("dd-MM-yyyy").format(tanggalLahir);
                    String JK = "";

                    if (radio1.isSelected()) {
                        JK = radio1.getText();
                    }
                    if (radio2.isSelected()) {
                        JK = radio2.getText();
                    }

                    String dataTabungan = listJenisTabungan.getSelectedValue() != null ? listJenisTabungan.getSelectedValue() : "Tidak ada tabungan yang dipilih";

                    String wnaStatus = isCheckBoxSelected ? "WNA : Ya" : "WNA : Bukan";
                    int frekuensiTransaksi = slider.getValue();

                    String biodata = "Nama: " + nama +
                            "\nNomor Telepon: " + noHp +
                            "\nTanggal Lahir : " + tanggal +
                            "\nJenis Kelamin : " + JK +
                            "\n" + wnaStatus +
                            "\nTabungan yang dipilih : " + dataTabungan +
                            "\nFrekuensi Transaksi : " + frekuensiTransaksi + "$" +
                            "\n" + "=".repeat(30) + "\n";

                    areaBiodata.append(biodata);
                    JOptionPane.showMessageDialog(null, "Berhasil Menambah Data", "Title", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        if (e.getSource() == tombolReset) {
            int konfirmasi = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            JPasswordField passwordField = new JPasswordField();
            int masukkanPassword = JOptionPane.showConfirmDialog(
                    this,
                    passwordField,
                    "Masukkan Password Anda,",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (masukkanPassword == JOptionPane.OK_OPTION) {
                char[] password = passwordField.getPassword();
                String passwordString = new String(password);

                if (passwordString.equals("P@ssw0rd")) {
                    if (konfirmasi == JOptionPane.YES_OPTION) {
                        kotakNama.setText("");
                        kotakNoHp.setText("");
                        radio1.setSelected(false);
                        radio2.setSelected(false);
                        checkBox.setSelected(false);
                        listJenisTabungan.clearSelection();
                        areaBiodata.setText("");
                    }
                    JOptionPane.showMessageDialog(null, "Berhasil Mereset Field", "Title", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LATIHAN_7_MOD();
        });
    }
}