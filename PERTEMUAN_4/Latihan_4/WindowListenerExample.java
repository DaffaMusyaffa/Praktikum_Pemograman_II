package PERTEMUAN_4.Latihan_4;

import javax.swing.*;
import java.awt.event.*;


public class WindowListenerExample {
  
public static void main(String[] args) {
  // Membuat Frame
  JFrame frame = new JFrame("WindowListener Example");

  // Membuat label untuk menampilkan pesan
  JLabel label = new JLabel("Lakukan operasi pada jendela.");
  label.setBounds(50, 50, 300, 30);

  //Menambahkan WindowListener ke frame
  frame.addWindowListener(new WindowListener() {
    //Dijalankan ketika jendela dibuka
    public void windowOpened(WindowEvent e) {
      label.setText("Window Opened.");
    }

  // Dijalankan ketika jendela sedang dalam proses penutupan
  public void windowClosing(WindowEvent e) {
    label.setText("Window Closing.");
    // Bisa menambahkan System.exit(0); jika ingin menghentikan program
  }

    // Dijalankan ketika jendela benar-benar ditutup
      public void windowClosed(WindowEvent e) {
        System.out.print("Window Closed.");
      }

// Dijalankan ketika jendela diminimalkan
    public void windowIconified(WindowEvent e) {
      label.setText("Window Minimized ");
    }
    // Dijalankan ketika jendela dipulihkan dari minimalkan
    public void windowDeiconified(WindowEvent e) {
      label.setText("Window Restored. ");
    }
    // Dijalankan ketika jendela menjadi aktif (berfokus)
    public void windowActivated(WindowEvent e) {
      label.setText("Window Restored. ");
    }
    // Dijalankan ketika jendela kehilangan fokus
    public void windowDeactivated(WindowEvent e) {
      label.setText("Window Deactivated. ");
    }
    });

    // Menambahkan label ke frame
    frame.add(label);

    // Setting frame
    frame.setSize(400, 200);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Agar jendela benar-benar tertutup
}
}
