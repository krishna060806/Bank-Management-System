package ui;

import dao.TransactionDAO;
import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class DepositFrame extends JFrame {

    User currentUser;

    JTextField amountField;

    public DepositFrame(User user) {

        currentUser = user;

        setTitle("Deposit Money");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JLabel amountLabel = new JLabel("Enter Amount");

        amountField = new JTextField();

        JButton depositButton = new JButton("Deposit");

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3, 1, 10, 10));

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(depositButton);

        add(panel);

        depositButton.addActionListener(e -> depositMoney());

        setVisible(true);
    }

    private void depositMoney() {

        double amount = Double.parseDouble(amountField.getText());

        double newBalance = currentUser.getBalance() + amount;

        UserDAO userDAO = new UserDAO();

        boolean updated = userDAO.updateBalance(currentUser.getId(), newBalance);

        if (updated) {

            currentUser.setBalance(newBalance);

            TransactionDAO transactionDAO = new TransactionDAO();

            transactionDAO.addTransaction(currentUser.getId(), "DEPOSIT", amount);

            JOptionPane.showMessageDialog(this,
                    "Amount Deposited Successfully");

            dispose();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Deposit Failed");
        }
    }
}