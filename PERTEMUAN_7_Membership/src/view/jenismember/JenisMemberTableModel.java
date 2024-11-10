package PERTEMUAN_7_Membership.src.view.jenismember;

import javax.swing.table.*;
import java.util.List;
import PERTEMUAN_7_Membership.src.model.JenisMember;

class JenisMemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama"};
        private List<JenisMember> data;

    public JenisMemberTableModel(List<JenisMember> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    
    public int getRowCount() {
        return data.size();
    }

    
    public String getColumnName(int col) {
        return columnNames[col];
    }

    
    public Object getValueAt(int row, int col) {
        JenisMember rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
        }
        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false; // Tidak dapat diedit
    }

    public void add(JenisMember value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}