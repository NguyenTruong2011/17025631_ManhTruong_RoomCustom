package com.example.danhsachdiadiem;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Place.class}, version = 1, exportSchema = false)
public abstract class PlaceDatabase extends RoomDatabase {
    public static PlaceDatabase db;
    private static String DB_Name = "database.sqlite";
    public synchronized static PlaceDatabase getInstance(Context context)
    {
        if(db == null)
        {
            db = Room.databaseBuilder(context.getApplicationContext(), PlaceDatabase.class, DB_Name)
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return db;
    }
    public abstract IMainDao mainDao();
}
