package com.example.blenditapp.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PersonEntity.class}, version = 1, exportSchema = false)
public abstract class DBHelper extends RoomDatabase {
    public abstract PersonDAO PersonDao();

    private static DBHelper INSTANCE;

    public static DBHelper getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DBHelper.class, "user-database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
