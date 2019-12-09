package com.abbaqus.vivekathreya_reddit.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.abbaqus.vivekathreya_reddit.model.PopularModel;

import java.util.List;

@Dao
public interface FavDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(PopularModel popularModel);

    @Query("SELECT * from favTable")
    LiveData<List<PopularModel>> getFavList();

    @Query("SELECT id from favTable Where title = :title")
    int ifTitleExists(String title);

    @Query("DELETE from favTable where id = :id")
    int deleteFav(int id);



}
