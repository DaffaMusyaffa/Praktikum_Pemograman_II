package PERTEMUAN_2.Latihan_4;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FormFrame extends JFrame implements ActionListener {
  private JTextField Kolom_Nama;
  private JTextField Kolom_NoHp;
  private JButton button;
  private JTextArea Tanda_Biodata;

  public FormFrame() {
    this.setTitle("PERTEMUAN_2 - Latihan 4");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400, 500);
    this.setLayout(null);

    JLabel LabelNama = new JLabel("Nama :");
      LabelNama.setBounds(50, 20, 100, 30);
      Kolom_Nama = new JTextField();
      Kolom_Nama.setBounds(120, 20, 200, 30);
    
    JLabel LabelNoHp = new JLabel("No Hp :");
    LabelNoHp.setBounds(50, 50, 100, 30);
    Kolom_NoHp = new JTextField();
    Kolom_NoHp.setBounds(120, 50, 200, 30);
    button = new JButton("Simpan");
    button.setBounds(120, 80, 100, 30);
    button.addActionListener(this);

    Tanda_Biodata = new JTextArea();
    Tanda_Biodata.setBounds(50, 130, 300, 250);
    Tanda_Biodata.setEditable(false);

    add(LabelNama);
    add(Kolom_Nama);
    add(LabelNoHp);
    add(Kolom_NoHp);
    add(button);
    add(Tanda_Biodata);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = Kolom_Nama.getText();
        String noHp = Kolom_NoHp.getText();

        String biodata = "Nama: " + nama + "\nNomor Telepon: " + noHp + "\n" + "=".repeat(30) + "\n";

        Tanda_Biodata.append(biodata);

        Kolom_Nama.setText("");
        Kolom_NoHp.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormFrame();
        });
    }
}
