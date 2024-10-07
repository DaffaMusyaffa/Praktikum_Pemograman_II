package PERTEMUAN_2.Latihan_5;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HelloCheckBox extends JFrame{
    private boolean checkBoxSelected;
    public HelloCheckBox(){
        this.checkBoxSelected = false;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel labelInput = new JLabel("Input Nama : ");
        labelInput.setBounds(15,40,350,10);

        JTextField textField = new JTextField();
        textField.setBounds(15,60,350,50);

        JCheckBox checkBox = new JCheckBox("Saya Menyetujui Syarat dan ketentuan yang berlaku");
        checkBox.setBounds(15,120,350,30);

        JButton button = new JButton("Simpan");
        button.setBounds(15,150,100,40);

        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(15,200,250,40);
        txtOutput.setEditable(false);

        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                checkBoxSelected = e.getStateChange() == 1;
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(checkBoxSelected){
                    String nama = textField.getText();
                    txtOutput.append("Hello " + nama + "\n");
                    textField.setText("");
                }else {
                    txtOutput.setText("Anda tidak mencentang kotak persetujuan\n");
                }
            }
        });
        add(button);
        add(textField);
        add(checkBox);
        add(labelInput);
        add(txtOutput);

        setSize(400,500);
        setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HelloCheckBox frame = new HelloCheckBox();
                frame.setVisible(true);
            }
        });
    }
}