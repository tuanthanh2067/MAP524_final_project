package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.Model.Meal;
import com.example.final_project.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity implements DatabaseManager.DatabaseListener {
    ArrayList<Meal> listFromDB = new ArrayList<>();
    RecyclerView recyclerView;
    MealsAdapter adapter;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.list_of_meals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbManager = ((myApp) getApplication()).getDatabaseManager();
        dbManager.listener = this;

        dbManager.getAllMeals();
        setTitle("My favorites");
    }


    @Override
    public void databaseAllMealListener(List<Meal> list) {
        listFromDB = new ArrayList<>(list);

        adapter = new MealsAdapter(this, listFromDB, new MealsAdapter.mealClickListener() {
            @Override
            public void mealClicked(Meal selectedMeal) {
                // TODO: show instruction

                Intent intent = new Intent(getApplicationContext(), InstructionActivity.class
                );

                Bundle bundle = new Bundle();
                bundle.putString("instruction", selectedMeal.getInstruction());
                intent.putExtra("bundle", bundle);

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
