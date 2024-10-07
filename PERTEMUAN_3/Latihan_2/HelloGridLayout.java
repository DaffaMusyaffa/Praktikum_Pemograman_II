package PERTEMUAN_3.Latihan_2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloGridLayout extends JFrame implements ActionListener {
    private JButton[] buttons;
    private boolean gameOver;

    public HelloGridLayout() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOver = false;

        // Create an array of buttons
        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }

        // Set the layout
        this.setLayout(new GridLayout(3, 3));
        this.setSize(300, 300);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            JButton button = (JButton) e.getSource();
            if (button.getText().isEmpty()) {
                button.setText("O");
                checkWinner();
                
                if (!gameOver) {
                    for (int i = 0; i < buttons.length; i++) {
                        if (buttons[i].getText().isEmpty()) {
                            buttons[i].setText("X");
                            break;
                        }
                    }
                    checkWinner();
                }
            }
        }
    }

    private void checkWinner() {
        String winner = "";

        // Rows check
        if (!buttons[0].getText().isEmpty() && buttons[0].getText().equals(buttons[1].getText()) && buttons[0].getText().equals(buttons[2].getText())) {
            winner = buttons[0].getText();
            highlightButtons(0, 1, 2);
        } else if (!buttons[3].getText().isEmpty() && buttons[3].getText().equals(buttons[4].getText()) && buttons[3].getText().equals(buttons[5].getText())) {
            winner = buttons[3].getText();
            highlightButtons(3, 4, 5);
        } else if (!buttons[6].getText().isEmpty() && buttons[6].getText().equals(buttons[7].getText()) && buttons[6].getText().equals(buttons[8].getText())) {
            winner = buttons[6].getText();
            highlightButtons(6, 7, 8);
        }
        
        // Columns check
        else if (!buttons[0].getText().isEmpty() && buttons[0].getText().equals(buttons[3].getText()) && buttons[0].getText().equals(buttons[6].getText())) {
            winner = buttons[0].getText();
            highlightButtons(0, 3, 6);
        } else if (!buttons[1].getText().isEmpty() && buttons[1].getText().equals(buttons[4].getText()) && buttons[1].getText().equals(buttons[7].getText())) {
            winner = buttons[1].getText();
            highlightButtons(1, 4, 7);
        } else if (!buttons[2].getText().isEmpty() && buttons[2].getText().equals(buttons[5].getText()) && buttons[2].getText().equals(buttons[8].getText())) {
            winner = buttons[2].getText();
            highlightButtons(2, 5, 8);
        }

        // Diagonals check
        else if (!buttons[0].getText().isEmpty() && buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())) {
            winner = buttons[0].getText();
            highlightButtons(0, 4, 8);
        } else if (!buttons[2].getText().isEmpty() && buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())) {
            winner = buttons[2].getText();
            highlightButtons(2, 4, 6);
        }

        if (!winner.isEmpty()) {
            gameOver = true;
            JOptionPane.showMessageDialog(this, "Winner is: " + winner);
        }
    }

    private void highlightButtons(int i1, int i2, int i3) {
        buttons[i1].setForeground(Color.RED);
        buttons[i2].setForeground(Color.RED);
        buttons[i3].setForeground(Color.RED);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HelloGridLayout());
    }
}