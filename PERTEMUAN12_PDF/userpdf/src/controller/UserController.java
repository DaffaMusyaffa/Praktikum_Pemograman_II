package controller;

import model.*;
import view.UserPdf;
import view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf) {
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }

    // Listener untuk menambahkan user
    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if (!name.isEmpty() && !email.isEmpty()) {
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    UserMapper mapper = session.getMapper(UserMapper.class);
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    mapper.insertUser(user);
                    session.commit(); // Commit transaksi
                    JOptionPane.showMessageDialog(view, "User added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }

    // Listener untuk me-refresh daftar user
    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try (SqlSession session = MyBatisUtil.getSqlSession()) {
                UserMapper mapper = session.getMapper(UserMapper.class);
                List<User> users = mapper.getAllUsers();
                String[] userArray = users.stream()
                        .map(u -> u.getName() + " (" + u.getEmail() + ")")
                        .toArray(String[]::new);
                view.setUserList(userArray);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
            }
        }
    }

    // Listener untuk mengekspor data user ke PDF
    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try (SqlSession session = MyBatisUtil.getSqlSession()) {
                UserMapper mapper = session.getMapper(UserMapper.class);
                List<User> users = mapper.getAllUsers();
                pdf.exportPdf(users);
                JOptionPane.showMessageDialog(view, "User data exported to PDF successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
            }
        }
    }
}
