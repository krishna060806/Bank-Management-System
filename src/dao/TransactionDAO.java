package dao;

import db.DBConnection;
import model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TransactionDAO {

    public boolean addTransaction(
            int userId,
            String type,
            double amount
    ) {

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    "INSERT INTO transactions(user_id,type,amount) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, userId);
            ps.setString(2, type);
            ps.setDouble(3, amount);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Transaction> getTransactions(int userId) {

        ArrayList<Transaction> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM transactions WHERE user_id=? ORDER BY txn_date DESC";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Transaction transaction =
                        new Transaction();

                transaction.setTxnId(
                        rs.getInt("txn_id")
                );

                transaction.setUserId(
                        rs.getInt("user_id")
                );

                transaction.setType(
                        rs.getString("type")
                );

                transaction.setAmount(
                        rs.getDouble("amount")
                );

                transaction.setTxnDate(
                        rs.getString("txn_date")
                );

                list.add(transaction);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}