package PERTEMUAN_7_Membership.src.view.main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonActionListener implements ActionListener {
    private MainFrame mainFrame;

    public MainButtonActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFrame.getButtonJenisMember()) {
            // Logika untuk ketika tombol Jenis Member ditekan
            mainFrame.showJenisMemberFrame();
        } else {
            // Logika untuk ketika tombol Member ditekan
            mainFrame.showMemberFrame();
        }
    }
}