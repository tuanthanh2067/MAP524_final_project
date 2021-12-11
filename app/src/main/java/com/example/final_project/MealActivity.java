package com.example.final_project;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.Model.Meal;
import com.example.final_project.database.DatabaseManager;
import com.example.final_project.database.MealDatabase;

public class MealActivity extends AppCompatActivity implements NetworkingService.NetworkingListener {
    TextView mealNameText;
    TextView categoryText;
    TextView areaText;
    ImageView imageView;
    Button addToFavorite;

    NetworkingService networkingManager;
    DatabaseManager dbManager;
    MealDatabase db;

    Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        networkingManager = ((myApp) getApplication()).getNetworkingService();
        networkingManager.listener = this;

        db = DatabaseManager.getDBInstance(this);
        dbManager = ((myApp) getApplication()).getDatabaseManager();

        if (getIntent().hasExtra("bundle")) {
            Bundle bundle = getIntent().getBundleExtra("bundle");
            meal = bundle.getParcelable("meal");
        }

        mealNameText = findViewById(R.id.mealNameText);
        categoryText = findViewById(R.id.categoryText);
        areaText = findViewById(R.id.areaText);
        imageView = findViewById(R.id.imageView);
        addToFavorite = findViewById(R.id.addToFavorite);

        mealNameText.setText("Meal Name: " + meal.getMealName());
        categoryText.setText("Category: " + meal.getCategory());
        areaText.setText("From: " + meal.getArea());

        networkingManager.getImageData(meal.getImage());

        addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.insertNewMeal(meal);

                // TODO: go back to previous page
            }
        });
    }

    @Override
    public void dataListener(String jsonString) {
    }

    @Override
    public void imageListener(Bitmap image) {
        imageView.setImageBitmap(image);
    }
}
