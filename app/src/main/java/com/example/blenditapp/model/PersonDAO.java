package com.example.blenditapp.model;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDAO {
    @Insert
    Long insert(PersonEntity u);

    @Query("SELECT * FROM `PersonEntity` ORDER BY `id` DESC")
    List<PersonEntity> getAll();

    @Query("SELECT * FROM `PersonEntity` WHERE `id` =:id")
    PersonEntity getPerson(int id);

    @Update
    void update(PersonEntity u);

    @Delete
    void delete(PersonEntity u);
}