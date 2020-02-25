package com.dmtroncoso.moviedb.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dmtroncoso.moviedb.data.local.dao.MovieDao;

@Database(entities = {PopularRoom.class}, version = 2, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    public abstract MovieDao getPopularDao();
}
