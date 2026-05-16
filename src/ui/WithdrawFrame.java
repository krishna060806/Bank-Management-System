package ui;

import dao.TransactionDAO;
import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class WithdrawFrame extends JFrame {

    User currentUser;

    JTextField amountField;

    public WithdrawFrame(User user) {

        currentUser = user;

        setTitle("Withdraw Money");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JLabel amountLabel = new JLabel("Enter Amount");

        amountField = new JTextField();

        JButton withdrawButton = new JButton("Withdraw");

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3, 1, 10, 10));

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);

        add(panel);

        withdrawButton.addActionListener(e -> {

            withdrawMoney();

        });

        setVisible(true);
    }

    private void withdrawMoney() {

        double amount =
                Double.parseDouble(amountField.getText());

        if (amount > currentUser.getBalance()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Insufficient Balance"
            );

            return;
        }

        double newBalance =
                currentUser.getBalance() - amount;

        UserDAO userDAO = new UserDAO();

        boolean updated =
                userDAO.updateBalance(
                        currentUser.getId(),
                        newBalance
                );

        if (updated) {

            currentUser.setBalance(newBalance);

            TransactionDAO transactionDAO =
                    new TransactionDAO();

            transactionDAO.addTransaction(
                    currentUser.getId(),
                    "WITHDRAW",
                    amount
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Withdrawal Successful"
            );

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Withdrawal Failed"
            );
        }
    }
}