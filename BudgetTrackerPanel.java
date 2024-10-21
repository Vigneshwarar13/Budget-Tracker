import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;

public class BudgetTrackerPanel extends JPanel {
    private List<Income> incomes;
    private List<Expense> expenses;
    private JTextField incomeField, sourceField, expenseField, categoryField;
    private JTextArea summaryArea;
    private JLabel linkLabel;

    public BudgetTrackerPanel() {
        // Initialize the lists
        incomes = new ArrayList<>();
        expenses = new ArrayList<>();
        
        // Set layout using GridBagLayout for more control
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Set padding

        // Income inputs (first column)
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;  // Align left
        add(new JLabel("Income Amount:"), gbc);
        
        gbc.gridx = 1;  // Move to second column
        incomeField = new JTextField(10);
        add(incomeField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Income Source:"), gbc);

        gbc.gridx = 1;  // Move to second column
        sourceField = new JTextField(10);
        add(sourceField, gbc);

        // Add Income button
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;  // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;  // Center the button
        JButton addIncomeButton = new JButton("Add Income");
        addIncomeButton.addActionListener(new AddIncomeListener());
        add(addIncomeButton, gbc);

        // Expense inputs (right of income inputs in the same rows)
        gbc.gridwidth = 1;  // Reset gridwidth
        gbc.gridx = 2; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;  // Align left
        add(new JLabel("Expense Amount:"), gbc);

        gbc.gridx = 3;  // Move to second column of the expense section
        expenseField = new JTextField(10);
        add(expenseField, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        add(new JLabel("Expense Category:"), gbc);

        gbc.gridx = 3;  // Move to second column of the expense section
        categoryField = new JTextField(10);
        add(categoryField, gbc);

        // Add Expense button
        gbc.gridx = 2; gbc.gridy = 2;
        gbc.gridwidth = 2;  // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(new AddExpenseListener());
        add(addExpenseButton, gbc);

        // Summary output
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 4;  // Span across the entire width
        summaryArea = new JTextArea(5, 30);
        summaryArea.setEditable(false);
        add(new JScrollPane(summaryArea), gbc);

        // View Summary button
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton summaryButton = new JButton("View Summary");
        summaryButton.addActionListener(new ViewSummaryListener());
        add(summaryButton, gbc);

        // Adding a clickable link
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        linkLabel = new JLabel("<html><a href='https://vigneshwarar-portfolio.netlify.app/'>Created By Me</a></html>");
        linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Set the cursor to hand on hover
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openWebPage("https://vigneshwarar-portfolio.netlify.app/");
            }
        });
        add(linkLabel, gbc);
    }

    // Method to open a web page in the default browser
    private void openWebPage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URI(urlString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Action listener for adding income
    private class AddIncomeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(incomeField.getText());
                String source = sourceField.getText();
                incomes.add(new Income(amount, source));
                incomeField.setText("");  // Clear input
                sourceField.setText("");
                JOptionPane.showMessageDialog(null, "Income added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for income.");
            }
        }
    }

    // Action listener for adding expense
    private class AddExpenseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(expenseField.getText());
                String category = categoryField.getText();
                expenses.add(new Expense(amount, category));
                expenseField.setText("");  // Clear input
                categoryField.setText("");
                JOptionPane.showMessageDialog(null, "Expense added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for expense.");
            }
        }
    }

    // Action listener for viewing summary
    private class ViewSummaryListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double totalIncome = 0;
            for (Income income : incomes) {
                totalIncome += income.getAmount();
            }

            double totalExpense = 0;
            for (Expense expense : expenses) {
                totalExpense += expense.getAmount();
            }

            double balance = totalIncome - totalExpense;
            summaryArea.setText("Total Income: $" + totalIncome + "\n" +
                                "Total Expenses: $" + totalExpense + "\n" +
                                "Balance: $" + balance);
        }
    }
}
