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
import java.nio.charset.Charset;
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
    private JSONArray jsonRecipies;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void sendData(Week week) {
        try {
            OutputStream ops = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(ops, Charset.forName("UTF-8"));

            jsonRecipies = new JSONArray();
            JSONObject weekObject = new JSONObject();
            weekObject.put("week", week.getDate());
            for (Weekday wkday : week.getWeekdays()) {
                JSONObject recipe = new JSONObject();
                recipe.put("name", wkday.getRecipe().getName());
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
                jsonRecipies.add(recipe);
            }
            weekObject.put("recipies", jsonRecipies);
            String output = weekObject.toJSONString();
            if (!socket.isClosed()) {
                osw.write(output);
            }
            osw.flush();
            osw.close();
        } catch (IOException ex) {
        }
    }
}
