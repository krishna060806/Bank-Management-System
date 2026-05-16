package ui;

import dao.TransactionDAO;
import model.Transaction;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TransactionHistoryFrame extends JFrame {

    User currentUser;

    JTable table;

    public TransactionHistoryFrame(User user) {

        currentUser = user;

        setTitle("Transaction History");

        setSize(700, 400);

        setLocationRelativeTo(null);

        String[] columns = {
                "Transaction ID",
                "Type",
                "Amount",
                "Date"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        table = new JTable(model);

        TransactionDAO dao =
                new TransactionDAO();

        ArrayList<Transaction> list =
                dao.getTransactions(currentUser.getId());

        for (Transaction t : list) {

            Object[] row = {

                    t.getTxnId(),
                    t.getType(),
                    t.getAmount(),
                    t.getTxnDate()
            };

            model.addRow(row);
        }

        JScrollPane pane =
                new JScrollPane(table);

        add(pane, BorderLayout.CENTER);

        setVisible(true);
    }
}