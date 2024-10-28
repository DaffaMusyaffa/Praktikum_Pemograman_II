import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Aplikasi Laundry");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem transaksiItem = new JMenuItem("Transaksi");
        transaksiItem.addActionListener(e -> new Transaksi());

        JMenuItem memberItem = new JMenuItem("Member");
        memberItem.addActionListener(e -> new Member());

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
