package PERTEMUAN_5.Latihan_3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Daffa
 */
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class JListExample {
public static void main(String[] args) {
JFrame frame = new JFrame("JList Example");
// Data untuk JList
String[] items = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
// Membuat JList dengan data
JList<String> list = new JList<>(items);
// Mengatur mode seleksi (dalam hal ini SINGLE_SELECTION)
list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
// Menambahkan listener untuk menangani seleksi item
list.addListSelectionListener(new ListSelectionListener() {
public void valueChanged(ListSelectionEvent e) {
if (!e.getValueIsAdjusting()) { // Mencegah pemicu ganda
String selectedItem = list.getSelectedValue();
System.out.println("Selected: " + selectedItem);

}
}
});
// Menambahkan JScrollPane untuk JList jika item terlalu banyak
JScrollPane scrollPane = new JScrollPane(list);
// Mengatur layout dan menambahkan komponen ke frame
frame.setLayout(null);
scrollPane.setBounds(50, 50, 150, 100);
frame.add(scrollPane);
// Konfigurasi frame
frame.setSize(300, 200);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
}
}