package com.example.final_project.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.final_project.Model.Meal;
import com.example.final_project.database.MealDAO;

@Database(version = 1,entities = {Meal.class})
public abstract class MealDatabase extends RoomDatabase{

    abstract public MealDAO getMealDAO();

}