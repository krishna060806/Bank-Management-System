package dao;

import db.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean registerUser(User user) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO users(name,email,password,balance) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDouble(4, user.getBalance());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public User loginUser(String email, String password) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getDouble("balance"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateBalance(int userId, double newBalance) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "UPDATE users SET balance=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setDouble(1, newBalance);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public double getBalance(int userId) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT balance FROM users WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getDouble("balance");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}