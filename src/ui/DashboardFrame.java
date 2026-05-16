package ui;

import model.User;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    User currentUser;

    public DashboardFrame(User user) {

        currentUser = user;

        setTitle("Dashboard");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Welcome " + user.getName());

        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton balanceButton = new JButton("Check Balance");
        JButton historyButton = new JButton("Transaction History");
        JButton logoutButton = new JButton("Logout");

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(5, 1, 15, 15));

        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(balanceButton);
        panel.add(historyButton);
        panel.add(logoutButton);

        add(welcomeLabel, BorderLayout.NORTH);

        add(panel, BorderLayout.CENTER);

        depositButton.addActionListener(e -> {

            new DepositFrame(currentUser);

        });
        withdrawButton.addActionListener(e -> {

            new WithdrawFrame(currentUser);

        });
        historyButton.addActionListener(e -> {

            new TransactionHistoryFrame(currentUser);

        });

        balanceButton.addActionListener(e ->

                JOptionPane.showMessageDialog(
                        this,
                        "Current Balance: ₹" + currentUser.getBalance()
                )
        );

        logoutButton.addActionListener(e -> {

            new LoginFrame();

            dispose();
        });

        setVisible(true);
    }
}