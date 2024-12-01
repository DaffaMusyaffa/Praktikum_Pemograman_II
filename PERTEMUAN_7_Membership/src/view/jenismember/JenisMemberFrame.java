package PERTEMUAN_7_Membership.src.view.jenismember;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import PERTEMUAN_7_Membership.src.model.JenisMember;
import PERTEMUAN_7_Membership.src.dao.JenisMemberDao;

public class JenisMemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private JTextField textFieldNama;
    private JenisMemberTableModel tableModel;
    private JenisMemberDao jenisMemberDao;
    private JTable table;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 100, 100, 40);

        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(120, 100, 100, 40);

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(225, 100, 100, 40);

        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 200);

        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        // Action listeners
        JenisMemberButtonSimpanActionListener simpanListener = new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);
        buttonSimpan.addActionListener(simpanListener);

        buttonUpdate.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String updatedNama = textFieldNama.getText();
                JenisMember selectedJenisMember = jenisMemberList.get(selectedRow);
                selectedJenisMember.setNama(updatedNama);

                jenisMemberDao.update(selectedJenisMember);
                tableModel.fireTableRowsUpdated(selectedRow, selectedRow);
            }
        });

        buttonDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                JenisMember selectedJenisMember = jenisMemberList.get(selectedRow);
                jenisMemberDao.delete(selectedJenisMember);
                jenisMemberList.remove(selectedRow);
                tableModel.fireTableRowsDeleted(selectedRow, selectedRow);
            }
        });

        this.add(buttonSimpan);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(scrollableTable);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public void addJenisMember(JenisMember jenisMember) {
        tableModel.add(jenisMember);
        textFieldNama.setText("");
    }
}
