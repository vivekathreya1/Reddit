package com.abbaqus.vivekathreya_reddit.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.abbaqus.vivekathreya_reddit.model.PopularModel;


@Database(entities = {PopularModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase ourInstance ;
    public abstract FavDao favDao();

    public static AppDatabase getAppDatabase(Context context) {

        if(ourInstance==null){
            ourInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "fav-database").build();
        }
        return ourInstance;
    }

    public static void destroyInstance() {
        ourInstance = null;
    }

}
