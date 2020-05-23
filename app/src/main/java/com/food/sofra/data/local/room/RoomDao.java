package com.food.sofra.data.local.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RoomDao {
    @Insert
    void add(Item ... mItem);

    @Update
    void onUpdate(Item ... mItem);

    @Delete
    void onDelete(Item ... mItem);

    @Query("select * from Item")
    List<Item> getAll();
    @Query("DELETE FROM Item")
    void deleteAll();

    /*@Query("Select * from Item")
    List<Item> getAll();*/
}
