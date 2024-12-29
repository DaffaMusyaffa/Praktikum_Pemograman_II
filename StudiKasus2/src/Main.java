import view.ItemView;
import controller.ItemController;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Run GUI on Event Dispatch Thread (EDT) to prevent concurrency issues
        SwingUtilities.invokeLater(() -> {
            // Create instance of ItemView and ItemController
            ItemView itemView = new ItemView();
            new ItemController(itemView);

            // Display ItemView in the center of the screen
            itemView.setLocationRelativeTo(null);
            itemView.setVisible(true);
        });
    }
}