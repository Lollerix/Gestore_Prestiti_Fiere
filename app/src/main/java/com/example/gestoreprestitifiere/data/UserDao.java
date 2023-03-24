package com.example.gestoreprestitifiere.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    public List<User> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertAll(User... users);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(User user);

    @Delete
    public void Delete(User user);

    @Update
    public void updateUsers(User... users);
}
