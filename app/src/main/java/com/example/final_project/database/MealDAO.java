package com.example.final_project.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.final_project.Model.Meal;

import java.util.List;

@Dao
public interface MealDAO {
    @Insert
    void insertNewMeal(Meal meal);

    @Query("SELECT * FROM Meal")
    List<Meal> getAll();
}