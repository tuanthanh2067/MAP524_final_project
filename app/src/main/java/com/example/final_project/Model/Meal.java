package com.example.final_project.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Meal implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String mealName;
    private String category;
    private String area;
    private String image;
    private String instruction;

    public Meal() {
        this.mealName = "";
        this.category = "";
        this.area = "";
        this.image = "";
        this.instruction = "";
    }

    @Ignore
    public Meal(String mealName, String category, String area, String image, String instruction) {
        this.mealName = mealName;
        this.category = category;
        this.area = area;
        this.image = image;
        this.instruction = instruction;
    }

    @Ignore
    protected Meal(Parcel in) {
        id = in.readInt();
        mealName = in.readString();
        category = in.readString();
        area = in.readString();
        image = in.readString();
        instruction = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(mealName);
        dest.writeString(category);
        dest.writeString(area);
        dest.writeString(image);
        dest.writeString(instruction);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }

    public int getId() {
        return id;
    }

    public String getMealName() {
        return mealName;
    }

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
    }

    public String getImage() {
        return image;
    }
}
