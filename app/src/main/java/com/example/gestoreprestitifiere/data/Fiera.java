package com.example.gestoreprestitifiere.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "fiera")
public class Fiera {

    @PrimaryKey
    @NonNull
    private final String nome;
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    private final Date created_time;

    public Fiera(@NonNull String nome, Date created_time) {
        this.nome = nome;
        this.created_time = created_time;
    }

    @Ignore
    public Fiera(String nome){
        this.nome = nome;
        Calendar cal = Calendar.getInstance();
        this.created_time = cal.getTime();
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public Date getCreated_time() {
        return created_time;
    }

}
