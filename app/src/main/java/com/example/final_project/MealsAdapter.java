package com.example.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.Model.Meal;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.TasksViewHolder> {

    interface mealClickListener {
        public void mealClicked(Meal selectedMeal);
    }

    private Context mCtx;
    public List<Meal> mealList;
    private final mealClickListener listener;

    public MealsAdapter(Context mCtx, List<Meal> mealList, mealClickListener listenerFromActivity) {
        this.mCtx = mCtx;
        this.mealList = mealList;
        listener = listenerFromActivity;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_meals, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        Meal t = mealList.get(position);
        holder.mealTextView.setText(t.getMealName() + " from " + t.getArea());
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }


    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mealTextView;

        public TasksViewHolder(View itemView) {
            super(itemView);

            mealTextView = itemView.findViewById(R.id.mealText);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Meal meal = mealList.get(getAdapterPosition());
            listener.mealClicked(meal);

        }
    }

}
