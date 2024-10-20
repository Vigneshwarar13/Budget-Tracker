import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set up the frame (main window)
        JFrame frame = new JFrame("Personal Budget Tracker");
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create an instance of BudgetTrackerPanel (our GUI panel)
        BudgetTrackerPanel panel = new BudgetTrackerPanel();
        frame.add(panel);  // Add panel to the frame

        // Make the frame visible
        frame.setVisible(true);
    }
}
