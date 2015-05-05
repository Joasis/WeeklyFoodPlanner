/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IngredientAmount;
import model.Week;
import model.Weekday;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Jonas
 */
public class ServerHandler {

    private Socket socket;
    private JSONArray json;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void sendData(Week week) {
//        InputStream input = null;
        try {
//            input = socket.getInputStream();
//            Scanner scan = new Scanner(input);
            OutputStream ops = socket.getOutputStream();
//            PrintWriter pw = new PrintWriter(output, true);
            OutputStreamWriter osw = new OutputStreamWriter(ops);
            ArrayList<String> list = new ArrayList<>();

            json = new JSONArray();
            for (Weekday wkday : week.getWeekdays()) {
                JSONObject recipe = new JSONObject();
                recipe.put("week", week.getDate());
                recipe.put("name", wkday.getRecipe().getName());
                recipe.put("date", wkday.getDate());
                recipe.put("omit", wkday.isOmit());
                recipe.put("active", wkday.getRecipe().isActive());
                JSONArray ingList = new JSONArray();
                for (IngredientAmount ingAm : wkday.getRecipe().getIngredientList()) {
                    JSONObject ingredient = new JSONObject();
                    ingredient.put("name", ingAm.getIngredient().getName());
                    ingredient.put("amount", ingAm.getAmount());
                    ingredient.put("unit", ingAm.getUnit().getShortname());
                    ingList.add(ingredient);
                }
                recipe.put("ingredientList", ingList);
                json.add(recipe);
            }
            String output = json.toJSONString();
            if (!socket.isClosed()) {
                osw.write(output);
//                String request = scan.nextLine();
//                String response = request + " (" + request.length() + ")";
//                pw.println("Du er forbundet");
            }
            osw.flush();
            osw.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
//            try {
//                input.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
}