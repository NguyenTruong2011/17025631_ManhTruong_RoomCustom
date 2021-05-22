package com.example.danhsachdiadiem;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface IMainDao {
    @Insert(onConflict = REPLACE)
    void insert(Place us);
    @Delete
    void delete(Place user);
    @Delete
    void deleteAll(List<Place> lstUs);

    @Query("UPDATE place SET Place = :sPlace WHERE Id = :sId")
    void update(int sId, String sPlace);
    @Query("SELECT * FROM place")
    List<Place> getAllUser();
}
