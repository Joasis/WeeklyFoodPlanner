/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.GUI;

/**
 *
 * @author Jonas
 */
public class Start {

    public Start() {
        try {
            ControlHandler ch = new ControlHandler();
            GUI gui = new GUI(ch);
            gui.setVisible(true);
        } catch (SQLException ex) {
            String errMsg = "Ukendt fejl " + ex.getErrorCode() + ", prøv igen senere";
            if (ex.getErrorCode() == 0) {
                errMsg = "Forbindelsen blev ikke fundet, kontroller at 'Host' og 'Port' er korrekt";
            } else if (ex.getErrorCode() == 1045) {
                errMsg = "Brugernavn og Password stemmer ikke";
            } else if (ex.getErrorCode() == 1049) {
                errMsg = "Databasen kunne ikke findes";
            }
            JOptionPane.showMessageDialog(null, "Indlæsning af ugeplan mislykkedes\nKunne ikke etablere forbindelse til databasen", "ADVARSEL", JOptionPane.ERROR_MESSAGE);
            GUI.decorateUI("Luk", "");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Indlæsning af ugeplan mislykkedes\nKunne ikke etablere forbindelse til databasen", "ADVARSEL", JOptionPane.ERROR_MESSAGE);
            GUI.decorateUI("Luk", "");
        }
    }
}
