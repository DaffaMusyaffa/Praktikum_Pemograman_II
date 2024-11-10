package PERTEMUAN_7_Membership.src.view.member;


import PERTEMUAN_7_Membership.src.dao.JenisMemberDao;
import PERTEMUAN_7_Membership.src.dao.MemberDao;
import PERTEMUAN_7_Membership.src.model.JenisMember;
import PERTEMUAN_7_Membership.src.model.Member;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<JenisMember> comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;
    private JTable table;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;

        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 350, 10);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 150, 30);

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 160, 100, 40);

        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(120, 160, 100, 40);

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(225, 160, 100, 40);

        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220, 350, 200);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        MemberButtonSimpanActionListener simpanListener = new MemberButtonSimpanActionListener(this, memberDao);
        buttonSimpan.addActionListener(simpanListener);

        buttonUpdate.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String updatedNama = textFieldNama.getText();
                JenisMember selectedJenisMember = (JenisMember) comboJenis.getSelectedItem();
                Member selectedMember = memberList.get(selectedRow);

                selectedMember.setNama(updatedNama);
                selectedMember.setJenisMember(selectedJenisMember);

                memberDao.update(selectedMember);
                tableModel.fireTableRowsUpdated(selectedRow, selectedRow);
            }
        });

        buttonDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Member selectedMember = memberList.get(selectedRow);
                memberDao.delete(selectedMember);
                memberList.remove(selectedRow);
                tableModel.fireTableRowsDeleted(selectedRow, selectedRow);
            }
        });

        this.add(buttonSimpan);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(scrollableTable);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember);
        }
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void addMember(Member member) {
        tableModel.add(member);
        textFieldNama.setText("");
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
