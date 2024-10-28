import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Aplikasi Laundry");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        
        // Form Transaksi
        JMenuItem transaksiItem = new JMenuItem("Transaksi");
        transaksiItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Transaksi();
            }
        });
        
        // Form Member
        JMenuItem memberItem = new JMenuItem("Member");
        memberItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Member();
            }
        });

        // Form Pengeluaran
        JMenuItem pengeluaranItem = new JMenuItem("Pengeluaran");
        pengeluaranItem.addActionListener(e -> new Pengeluaran());

        menu.add(transaksiItem);
        menu.add(memberItem);
        menu.add(pengeluaranItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
