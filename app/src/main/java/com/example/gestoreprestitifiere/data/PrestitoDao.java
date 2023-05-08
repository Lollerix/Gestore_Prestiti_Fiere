package com.example.gestoreprestitifiere.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Dao
public interface PrestitoDao {

    /**
     * Restituisce la lista dei giochi attualmente in prestito
     * @param userName
     * @param giocoName
     * @return
     */
    @Query("SELECT * FROM prestito " +
            "WHERE prestito.returned = 0 AND prestito.gioco_name LIKE :giocoName AND" +
            " prestito.user_ID LIKE :userName")
    public List<Prestito> loadGiocoConUser(int userName, String giocoName);

    @Query("SELECT * FROM gioco " +
            "WHERE gioco.nome NOT IN " +
            "(SELECT Prestito.gioco_name FROM Prestito WHERE returned = 0)" +
            "ORDER BY nome")
    public List<Gioco> getFree();

    @Query("SELECT * FROM prestito " +
            "ORDER BY created_at")
    public List<Prestito> getAll();

    @Query("SELECT * FROM prestito " +
            "WHERE prestito.returned = 0 " +
            "ORDER BY created_at")
    public List<Prestito> getOccupati();
    @Query("SELECT * FROM prestito " +
            "WHERE prestito.returned = 1 " +
            "ORDER BY created_at")
    public List<Prestito> getTornati();

    @Query("INSERT INTO prestito(user_ID, gioco_name, user_name, fiera_name, created_at) " +
            "VALUES(:userID, :giocoName, :userName, :fieraName, :date)")
    public void insertPrestito(int userID, String giocoName, String userName,
                               String fieraName, Date date);

    @Query("SELECT * FROM prestito" +
            " WHERE id = :id")
    public Prestito getPrestito(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void Insert(Prestito prestito);

    @Update
    public void Update(Prestito prestito);

    @Delete
    public void Delete(Prestito prestito);


}

