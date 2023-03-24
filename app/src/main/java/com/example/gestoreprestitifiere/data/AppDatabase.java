package com.example.gestoreprestitifiere.data;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {User.class, Gioco.class, GiocoUser.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    @Override
    public void clearAllTables() {

    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }

    public abstract UserDao userDao();
    public abstract GiocoUserDao giocoUserDao();
    public abstract GiocoDao giocoDao();
}
