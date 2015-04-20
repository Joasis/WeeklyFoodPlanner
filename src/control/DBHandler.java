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
import model.Weekday;

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
            db = "wfoodplanner";
        } catch (SQLException ex) {
            String errMsg = "Ukendt fejl " + ex.getErrorCode() + ", prøv igen senere";
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

    public void updateWeekdayOmit(Weekday weekday) throws SQLException {
        String sql = "UPDATE wkday set wkd_omit = " + weekday.isOmit() + " where wkd_id = " + weekday.getId();
        if (stmt != null) {
            stmt.execute(sql);
        }
    }

    public void updateWeekdayDate(Weekday weekday) throws SQLException {
        String sql = "UPDATE wkday set wkd_date = " + weekday.getDateByCal().getTimeInMillis() + " where wkd_id = " + weekday.getId();
        if (stmt != null) {
            System.out.println(sql);
            stmt.execute(sql);
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

    public void updateIngredient(Ingredient ing) throws SQLException {
        String sql = "update ingr SET in_name = '" + ing.getName() + "' where in_id = " + ing.getId();
        if (stmt != null) {
            stmt.execute(sql);
            System.out.println(sql);
        }
    }

    public void deleteIngredient(Ingredient ing) throws SQLException {
        String sql = "DELETE from ingr where in_id = " + ing.getId();
        if (stmt != null) {
            stmt.execute(sql);
        }
    }

    public int getNextAI(String table) throws SQLException, NullPointerException {
        int nextAI = -1;
        String sql = "select AUTO_INCREMENT as AI from INFORMATION_SCHEMA.TABLES "
                + "where TABLE_SCHEMA = '" + db + "' "
                + "and TABLE_NAME = '" + table + "'";
        ResultSet rs = stmt.executeQuery(sql);

        rs.next();
        nextAI = rs.getInt("AI");
        return nextAI;
    }

    public void insertRecipe(Recipe recipe) throws SQLException {
        // INSERT into recipe (re_name, re_description, re_portions, re_cooktime) values ("Pizza", "Just-eat - meget nemmere", 1, 10)
        String sql = "INSERT into recipe (re_id, re_name, re_description, re_portions, re_cooktime) values (" + recipe.getId() + ",'" + recipe.getName() + "', '" + recipe.getDescription() + "', " + recipe.getPortions() + ", " + recipe.getCookingtime() + ")";
        if (stmt != null) {
            stmt.addBatch(sql);
            for (IngredientAmount tempIngAm : recipe.getIngredientList()) {
                String sqlIng = "INSERT into amount values (" + recipe.getId() + ", " + tempIngAm.getIngredient().getId() + ", " + tempIngAm.getAmount() + ", " + tempIngAm.getUnit().getId() + ")";
                stmt.addBatch(sqlIng);
            }
            stmt.executeBatch();
        }
    }

    public void updateRecipe(Recipe recipe) throws SQLException {
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

    public void insertIngredientToRecipe(Recipe recipe, IngredientAmount ingAm) throws SQLException {
        String sql = "INSERT into amount values "
                + "(" + recipe.getId() + ", " + ingAm.getIngredient().getId()
                + ", " + ingAm.getAmount() + ", " + ingAm.getUnit().getId() + ");";
        if (stmt != null) {
            System.out.println(sql);
            stmt.execute(sql);
        }
    }
    public void insertIngredient(Ingredient ing) throws SQLException {
        String sql = "INSERT into ingr values("+ing.getId()+",'"+ing.getName()+"')";
        if (stmt != null) {
            System.out.println(sql);
            stmt.execute(sql);
        }
    }
}
