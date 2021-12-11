package com.example.final_project;

import com.example.final_project.Model.Meal;

import org.json.*;
import java.util.ArrayList;

public class JsonService {

    public ArrayList<Meal> getMealsFromJSON(String json)  {
        ArrayList<Meal> arrayList = new ArrayList<>(0);
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray arr = obj.getJSONArray("meals");

            for (int i = 0 ; i< arr.length(); i++) {
                String id = arr.getJSONObject(i).getString("idMeal");
                String mealName = arr.getJSONObject(i).getString("strMeal");
                String category = arr.getJSONObject(i).getString("strCategory");
                String area = arr.getJSONObject(i).getString("strArea");
                String image = arr.getJSONObject(i).getString("strMealThumb");
                String instruction = arr.getJSONObject(i).getString("strInstructions");

                Meal meal = new Meal(mealName, category, area, image, instruction);
                arrayList.add(meal);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

}