/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Unit;

/**
 *
 * @author Jonas
 */
public class UnitHandler {

    private final ArrayList<Unit> unitList;

    public UnitHandler() {
        unitList = new ArrayList<>();
        getUnitsFromDB();
    }

    public void getUnitsFromDB() {
        try {
            ResultSet rs = Start.dbh.selectAll("Unit");
            while (rs.next()) {
                Unit unit = new Unit(rs.getInt("un_id"), rs.getString("un_name"), rs.getString("un_shortname"));
                unitList.add(unit);
            }
        } catch (SQLException ex) {
            System.out.println("SQL FEJL" + ex);
        }
    }

    public ArrayList<Unit> getUnitList() {
        return unitList;
    }
}
