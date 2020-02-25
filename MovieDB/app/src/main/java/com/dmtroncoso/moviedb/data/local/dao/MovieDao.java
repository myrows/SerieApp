package com.dmtroncoso.moviedb.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dmtroncoso.moviedb.data.local.PopularRoom;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM popular")
    LiveData<List<PopularRoom>> loadSeries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePopular(List<PopularRoom> popularList);
}
