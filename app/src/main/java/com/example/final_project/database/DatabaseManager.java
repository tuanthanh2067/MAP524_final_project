package com.example.final_project.database;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import com.example.final_project.Model.Meal;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseManager {
    static MealDatabase db;
    ExecutorService databaseExecuter = Executors.newFixedThreadPool(4);
    Handler db_handler = new Handler(Looper.getMainLooper());

    public interface DatabaseListener {
        void databaseAllMealListener(List<Meal> list);
    }

    public DatabaseListener listener;


    private static void BuildDBInstance (Context context) {
        db = Room.databaseBuilder(context,MealDatabase.class,"meal_db").build();
    }
    public static MealDatabase getDBInstance(Context context){
        if (db == null){
            BuildDBInstance(context);
        }
        return db;
    }

    public void insertNewMeal(Meal d){
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                db.getMealDAO().insertNewMeal(d);
            }
        });
    }

    public void getAllMeals(){
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                List<Meal> list =  db.getMealDAO().getAll();
                db_handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.databaseAllMealListener(list);
                    }
                });
            }
        });
    }
}
