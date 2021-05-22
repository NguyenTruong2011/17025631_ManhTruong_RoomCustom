package com.example.danhsachdiadiem;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "place")
public class Place {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Place")
    private String place;

    public Place(int id, String place) {
        this.id = id;
        this.place = place;
    }

    public Place(String place) {
        this.place = place;
    }

    public Place() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

