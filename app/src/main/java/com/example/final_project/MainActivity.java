package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.final_project.Model.Meal;
import com.example.final_project.database.DatabaseManager;
import com.example.final_project.database.MealDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetworkingService.NetworkingListener {

    ArrayList<Meal> meals = new ArrayList<>();
    RecyclerView recyclerView;
    MealsAdapter adapter;
    NetworkingService networkingManager;
    JsonService jsonService;
    MealDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DatabaseManager.getDBInstance(this);

        networkingManager = ((myApp)getApplication()).getNetworkingService();
        jsonService = ((myApp)getApplication()).getJsonService();

        networkingManager.listener = this;

        recyclerView = findViewById(R.id.mealsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        adapter = new MealsAdapter(this, meals);
//        recyclerView.setAdapter(adapter);

        networkingManager.searchForRandomMeal();
        setTitle("Our meals");
    }

    @Override
    public void dataListener(String jsonString) {
        meals = jsonService.getMealsFromJSON(jsonString);
        adapter = new MealsAdapter(this, meals, new MealsAdapter.mealClickListener() {
            @Override
            public void mealClicked(Meal selectedMeal) {
                Intent intent = new Intent(getApplicationContext(), MealActivity.class);

                Bundle bundle = new Bundle();
                bundle.putParcelable("meal", selectedMeal);
                intent.putExtra("bundle", bundle);

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.favorites: {
                Intent intent = new Intent(this, FavoriteActivity.class);
                startActivity(intent);

                break;
            }
        }

        return true;
    }

    @Override
    public void imageListener(Bitmap image) {

    }

//    @Override
//    public void mealClicked(Meal selectedMeal) {
//
//    }

}