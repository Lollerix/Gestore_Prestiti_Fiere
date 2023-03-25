package com.example.gestoreprestitifiere.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface GiocoUserDao {

    /**
     * Restituisce la lista dei giochi attualmente in prestito
     * @param userName
     * @param giocoName
     * @return
     */
    @Query("SELECT * FROM giochi_prestati " +
            "WHERE giochi_prestati.returned = 0 AND giochi_prestati.gioco_name LIKE :giocoName AND" +
            " giochi_prestati.user_ID LIKE :userName")
    public List<GiocoUser> loadGiocoConUser(int userName, String giocoName);

    @Query("SELECT * FROM gioco " +
            "WHERE gioco.nome NOT IN " +
            "(SELECT giochi_prestati.gioco_name FROM giochi_prestati)")
    public List<Gioco> getFree();

    @Insert
    public void Insert(GiocoUser giocoUser);


    @Delete
    public void Delete(GiocoUser giocoUser);


}

