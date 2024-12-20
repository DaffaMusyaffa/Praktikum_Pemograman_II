package controller;

import model.*;
import view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class UserController {

    private UserView view;

    public UserController(UserView view) {
        this.view = view;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if (!name.isEmpty() && !email.isEmpty()) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    UserMapper mapper = session.getMapper(UserMapper.class);
                    mapper.insertUser(user);
                    session.commit();
                    JOptionPane.showMessageDialog(view, "User added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }

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
}

