package com.example.gestoreprestitifiere.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface GiocoDao {

    @Query("SELECT * FROM gioco")
    public List<Gioco> getAll();
    @Query("SELECT * FROM GIOCO WHERE gioco.nome LIKE :name")
    public Gioco getGioco(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(Gioco... giochi);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Gioco gioco);

    @Delete
    public void Delete(Gioco gioco);

    @Update
    public void updateGiochi(Gioco... giochi);
}
