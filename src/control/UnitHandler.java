/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public Unit getUnit(int unitID) {
        Unit unit = null;
        boolean unitFound = false;
        for (int i = 0; i < unitList.size() && unitFound == false; i++) {
            if (unitList.get(i).getId() == unitID) {
                unit = unitList.get(i);
                unitFound = true;
            }
        }
        return unit;
    }

    public ArrayList<Unit> getUnitList() {
        return unitList;
    }
}
