package com.example.gestoreprestitifiere.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FieraDao {

    @Query("SELECT * FROM fiera WHERE fiera.nome like (:name)")
    public Fiera getFiera(String name);

    @Query("SELECT * FROM fiera")
    public List<Fiera> getAll();

    @Query("SELECT COUNT(*) FROM prestito where prestito.fiera_name like (:name)")
    public int countPrestitiFiera(String name);

    @Query("INSERT INTO fiera(nome) VALUES (:name)")
    public void insertFiera(String name);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void Insert(Fiera fiera);

}
