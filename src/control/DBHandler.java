/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;

/**
 *
 * @author Jonas
 */
public class DBHandler {

    private static Connection conn;
    protected static Statement stmt;
    private String db;

    public DBHandler(String host, String pnr, String db, String usr, String pwd, JLabel label) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + pnr + "/" + db;
            conn = DriverManager.getConnection(url, usr, pwd);
            stmt = conn.createStatement();
            this.db = db;
        } catch (SQLException ex) {
            String errMsg = "Ukendt fejl " + ex.getErrorCode() + ", prøv igen senere";;
            if (ex.getErrorCode() == 0) {
                errMsg = "Forbindelsen blev ikke fundet, kontroller at 'Host' og 'Port' er korrekt";
            } else if (ex.getErrorCode() == 1045) {
                errMsg = "Brugernavn og Password stemmer ikke";
            } else if (ex.getErrorCode() == 1049) {
                errMsg = "Databasen kunne ikke findes";
            }
            label.setText(errMsg);
        } catch (ClassNotFoundException ex) {
            label.setText("Fejl i driver til databasen, kontakt din programmør");
        }
    }

    public DBHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/wfoodplanner";
            conn = DriverManager.getConnection(url, "root", "");
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            String errMsg = "Ukendt fejl " + ex.getErrorCode() + ", prøv igen senere";;
            if (ex.getErrorCode() == 0) {
                errMsg = "Forbindelsen blev ikke fundet, kontroller at 'Host' og 'Port' er korrekt";
            } else if (ex.getErrorCode() == 1045) {
                errMsg = "Brugernavn og Password stemmer ikke";
            } else if (ex.getErrorCode() == 1049) {
                errMsg = "Databasen kunne ikke findes";
            }
            System.out.println(errMsg);
        } catch (ClassNotFoundException ex) {
            System.out.println("Fejl i driver til databasen, kontakt din programmør");
        }
    }

    public ResultSet selectAll(String table) throws SQLException {
        String sql = "SELECT * FROM " + table;
        ResultSet rs = null;
        if (stmt != null) {
            rs = stmt.executeQuery(sql);
        }
        return rs;
    }
}
