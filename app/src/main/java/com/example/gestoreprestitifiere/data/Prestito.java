package com.example.gestoreprestitifiere.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;


@Entity(tableName = "prestito")
public class Prestito {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private final Integer id;
    @ColumnInfo
    @NonNull
    private int user_ID;
    @ColumnInfo
    @NonNull
    private String gioco_name;
    @ColumnInfo
    @NonNull
    private String user_name;


    @ColumnInfo
    @NonNull
    private String fiera_name;


    @ColumnInfo (defaultValue =  "0")
    private boolean returned;


    @ColumnInfo (defaultValue =  "CURRENT_TIMESTAMP")
    @NonNull
    private Date created_at;

    @ColumnInfo
    private Date returned_at;

    public int getUser_ID() {
        return user_ID;
    }

    @NonNull
    public String getGioco_name() {
        return gioco_name;
    }

    @NonNull
    public String getUser_name() {
        return user_name;
    }

    @NonNull
    public String getFiera_name() {
        return fiera_name;
    }

    public boolean isReturned() {
        return returned;
    }

    @NonNull
    public Date getCreated_at() {
        return created_at;
    }

    public Date getReturned_at() {
        return returned_at;
    }

    public void setReturned_at(Date returned_at) {
        this.returned_at = returned_at;
    }

    public Prestito(int id, int user_ID, @NonNull String gioco_name, @NonNull String user_name, String fiera_name, boolean returned, @NonNull Date created_at) {
        this.id = id;
        this.user_ID = user_ID;
        this.gioco_name = gioco_name;
        this.user_name = user_name;
        this.fiera_name = fiera_name;
        this.returned = returned;
        this.created_at = created_at;
    }

    public Prestito(Gioco gioco, User user, Fiera fiera) {
        this.id = 0;
        this.user_ID = user.getId();
        this.user_name = user.getNome();
        this.gioco_name = gioco.getNome();
        this.fiera_name = fiera.getNome();
        this.returned = false;
        Calendar cal = Calendar.getInstance();
        this.created_at = cal.getTime();
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public void setGioco_name(@NonNull String gioco_name) {
        this.gioco_name = gioco_name;
    }

    public void setUser_name(@NonNull String user_name) {
        this.user_name = user_name;
    }

    public void setFiera_id(String fiera_name) {
        this.fiera_name = fiera_name;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public void setCreated_at(@NonNull Date created_at) {
        this.created_at = created_at;
    }

    @NonNull
    public Integer getId() {
        return id;
    }
}
