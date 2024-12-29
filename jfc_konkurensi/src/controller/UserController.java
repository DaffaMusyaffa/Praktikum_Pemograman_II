package controller;

import model.*;
import view.*;
import javax.swing.*;
import java.awt.event.*;
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
        refreshUserList(); // Load initial data
    }

    private void refreshUserList() {
        new RefreshListener().actionPerformed(null);
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();

            if (!name.isEmpty() && !email.isEmpty()) {
                view.disableControlsDuringOperation(true);
                view.updateStatus("Adding user...");

                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        SqlSession session = MyBatisUtil.getSqlSession();
                        try {
                            UserMapper mapper = session.getMapper(UserMapper.class);
                            User user = new User();
                            user.setName(name);
                            user.setEmail(email);
                            mapper.insertUser(user);
                            session.commit();
                            return null;
                        } finally {
                            session.close();
                        }
                    }

                    @Override
                    protected void done() {
                        try {
                            get();
                            view.clearInputs();
                            refreshUserList();
                            view.updateStatus("User added successfully");
                        } catch (Exception ex) {
                            view.updateStatus("Failed to add user");
                            JOptionPane.showMessageDialog(view, 
                                "Error adding user: " + ex.getMessage());
                        } finally {
                            view.disableControlsDuringOperation(false);
                        }
                    }
                };
                worker.execute();
            } else {
                JOptionPane.showMessageDialog(view, "Please fill all fields");
            }
        }
    }

class RefreshListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        view.disableControlsDuringOperation(true);
        view.updateStatus("Loading users...");

        SwingWorker<List<User>, Integer> worker = new SwingWorker<>() {
            @Override
            protected List<User> doInBackground() throws Exception {
                // Simulasi loading progress seperti di PDF
                for (int i = 0; i <= 90; i += 10) {
                    Thread.sleep(100); // Simulasi delay
                    publish(i);
                }
                
                // Ambil data user
                List<User> users = mapper.getAllUsers();
                
                // Loading selesai
                publish(100);
                return users;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int latestProgress = chunks.get(chunks.size() - 1);
                view.updateProgress(latestProgress);
                view.updateStatus("Loading users... " + latestProgress + "%");
            }

            @Override
            protected void done() {
                try {
                    List<User> users = get();
                    String[] userArray = users.stream()
                        .map(u -> u.getName() + " (" + u.getEmail() + ")")
                        .toArray(String[]::new);
                    view.setUserList(userArray);
                    view.updateStatus("Data loaded successfully");
                } catch (Exception ex) {
                    view.updateStatus("Failed to load data");
                    JOptionPane.showMessageDialog(view, 
                        "Error loading users: " + ex.getMessage());
                } finally {
                    view.disableControlsDuringOperation(false);
                    view.updateProgress(0);
                }
            }
        };
        worker.execute();
    }
}

    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.disableControlsDuringOperation(true);
            view.updateStatus("Exporting to PDF...");

            SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    List<User> users = mapper.getAllUsers();
                    for (int i = 0; i <= 100; i += 10) {
                        Thread.sleep(100); // Simulate progress
                        publish(i);
                    }
                    pdf.exportPdf(users);
                    return null;
                }

                @Override
                protected void process(List<Integer> chunks) {
                    view.updateProgress(chunks.get(chunks.size() - 1));
                }

                @Override
                protected void done() {
                    try {
                        get();
                        view.updateStatus("Export completed");
                        JOptionPane.showMessageDialog(view, "PDF exported successfully");
                    } catch (Exception ex) {
                        view.updateStatus("Export failed");
                        JOptionPane.showMessageDialog(view,
                            "Error exporting PDF: " + ex.getMessage());
                    } finally {
                        view.disableControlsDuringOperation(false);
                        view.updateProgress(0);
                    }
                }
            };
            worker.execute();
        }
    }
}