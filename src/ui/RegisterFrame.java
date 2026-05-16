package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    JTextField nameField;
    JTextField emailField;
    JPasswordField passwordField;

    public RegisterFrame() {

        setTitle("Register");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");

        nameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();

        JButton registerButton = new JButton("Create Account");

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(emailLabel);
        panel.add(emailField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(registerButton);

        add(panel);

        registerButton.addActionListener(e -> registerUser());

        setVisible(true);
    }

    private void registerUser() {

        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        User user = new User(name, email, password, 0);

        UserDAO dao = new UserDAO();

        boolean status = dao.registerUser(user);

        if (status) {
            JOptionPane.showMessageDialog(this, "Account Created");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed");
        }
    }
}