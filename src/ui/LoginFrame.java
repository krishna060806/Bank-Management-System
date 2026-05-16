package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    JTextField emailField;
    JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Bank Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");

        emailField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        panel.add(emailLabel);
        panel.add(emailField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);

        loginButton.addActionListener(e -> loginUser());

        registerButton.addActionListener(e -> new RegisterFrame());

        setVisible(true);
    }

    private void loginUser() {

        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        UserDAO dao = new UserDAO();

        User user = dao.loginUser(email, password);

        if (user != null) {

            JOptionPane.showMessageDialog(this, "Login Successful");

            new DashboardFrame(user);

            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials");
        }
    }
}