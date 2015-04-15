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
import java.util.ArrayList;
import javax.swing.JLabel;
import model.Ingredient;
import model.IngredientAmount;
import model.Recipe;

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
            conn = DriverManager.getConnection(url, "root", "root");
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

    public void deleteRecipe(Recipe recipe) throws SQLException {
        String sql = "UPDATE recipe SET re_active = false where re_id = " + recipe.getId();
        if (stmt != null) {
            stmt.execute(sql);
        }
    }

    public void deleteIngredientFromRecipe(Recipe recipe, Ingredient ingredient) throws SQLException {
        String sql = "DELETE from amount where fk_recipe = " + recipe.getId() + " and fk_ingr = " + ingredient.getId();
        if (stmt != null) {
            stmt.execute(sql);
        }
    }

    public void update(Recipe recipe) throws SQLException {
        ArrayList<String> sqlQueries;
        sqlQueries = new ArrayList<>();
        String sql;
        for (IngredientAmount ingAm : recipe.getIngredientList()) {
            sql = "UPDATE amount set am_amount = " + ingAm.getAmount() + " where fk_recipe = " + recipe.getId() + " and fk_ingr = " + ingAm.getIngredient().getId();
            sqlQueries.add(sql);
            sql = "UPDATE amount set fk_am_unittype = " + ingAm.getUnit().getId() + " where fk_recipe = " + recipe.getId() + " and fk_ingr = " + ingAm.getIngredient().getId();
            sqlQueries.add(sql);
        }

        sql = "UPDATE recipe SET re_name = '" + recipe.getName() + "' where re_id = " + recipe.getId();
        sqlQueries.add(sql);
        sql = "UPDATE recipe SET re_description = '" + recipe.getDescription() + "' where re_id = " + recipe.getId();
        sqlQueries.add(sql);
        sql = "UPDATE recipe SET re_portions = " + recipe.getPortions() + " where re_id = " + recipe.getId();
        sqlQueries.add(sql);
        sql = "UPDATE recipe SET re_cooktime = " + recipe.getCookingtime() + " where re_id = " + recipe.getId();
        sqlQueries.add(sql);
        System.out.println("antal queries" + sqlQueries.size());
        for (String sqlQuery : sqlQueries) {
            stmt.addBatch(sqlQuery);
        }
        if (stmt != null) {
            stmt.executeBatch();
        }
    }
}
