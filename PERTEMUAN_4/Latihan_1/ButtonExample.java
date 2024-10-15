package PERTEMUAN_4.Latihan_1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;

public class ButtonExample {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Button Example");
    JButton button = new JButton("Click Me");

    //Menambahkan ActionListener ke JButton
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked!");
      }
    });

    button.setBounds(50, 50, 150, 30);
    frame.add(button);
    frame.setSize(300, 200);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}