package PERTEMUAN_3.Latihan_5;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.*;

public class FormBiodata extends JFrame implements ActionListener, ItemListener {

    private JTextField kotakNama;
    private JTextField kotakNoHp;
    private JButton tombol;
    private JCheckBox checkBox;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private ButtonGroup bg;
    private boolean isCheckBoxSelected;

    public FormBiodata() {
        this.setTitle("Form Biodata");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300); 
        this.setLayout(null); 

        // Tambahkan judul "Form Biodata"
        JLabel formTitle = new JLabel("Form Biodata", SwingConstants.CENTER); 
        formTitle.setFont(new Font("Arial", Font.BOLD, 20)); 
        formTitle.setBounds(0, 10, 500, 30); // Lebar penuh form, dan diatur tinggi atasnya

        // Label Nama
        JLabel LabelNama = new JLabel("Nama:");
        LabelNama.setBounds(100, 60, 100, 30);
        kotakNama = new JTextField();
        kotakNama.setBounds(180, 60, 120, 30);

        // Label Nomor HP
        JLabel LabelNoHp = new JLabel("Nomor HP:");
        LabelNoHp.setBounds(100, 100, 100, 30);
        kotakNoHp = new JTextField();
        kotakNoHp.setBounds(180, 100, 120, 30);

        // Label Jenis Kelamin
        JLabel jenisKelamin = new JLabel("Jenis Kelamin:");
        jenisKelamin.setBounds(320, 60, 100, 30);  
        radio1 = new JRadioButton("Laki-Laki");
        radio1.setBounds(320, 90, 100, 30);  
        radio2 = new JRadioButton("Perempuan");
        radio2.setBounds(320, 120, 100, 30);  
        bg = new ButtonGroup();
        bg.add(radio1);
        bg.add(radio2);

        // Warga Negara Asing Checkbox
        checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(320, 150, 200, 30);
        checkBox.addItemListener(this);

        // Tombol Simpan
        tombol = new JButton("Simpan");
        tombol.setBounds(230, 200, 100, 30);
        tombol.addActionListener(this);

        // Menambahkan elemen ke dalam frame
        add(formTitle); // Tambahkan judul form di atas
        add(LabelNama);
        add(kotakNama);
        add(LabelNoHp);
        add(kotakNoHp);
        add(jenisKelamin);
        add(radio1);
        add(radio2);
        add(checkBox);
        add(tombol);

        // Membuat posisi elemen-elemen berada di tengah layar
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        isCheckBoxSelected = e.getStateChange() == ItemEvent.SELECTED;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = kotakNama.getText();
        String noHp = kotakNoHp.getText();
        String JK = "";

        if (radio1.isSelected()) {
            JK = radio1.getText();
        } else if (radio2.isSelected()) {
            JK = radio2.getText();
        }

        String wnaStatus;
        if (isCheckBoxSelected) {
            wnaStatus = "WNA: Ya";
        } else {
            wnaStatus = "WNA: Tidak";
        }

        String biodata = "Nama: " + nama + "\nNomor Telepon: " + noHp + "\nJenis Kelamin: " + JK + "\n" + wnaStatus + "\n";

        JOptionPane.showMessageDialog(this, biodata, "Data Biodata", JOptionPane.INFORMATION_MESSAGE);

        kotakNama.setText("");
        kotakNoHp.setText("");
        bg.clearSelection(); // Clear radio button selection
        checkBox.setSelected(false); // Uncheck checkbox
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormBiodata();
        });
    }
}