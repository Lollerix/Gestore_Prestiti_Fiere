package com.example.gestoreprestitifiere.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "giochi_prestati", primaryKeys = {"user_ID", "gioco_name"})
public class GiocoUser {

    @ColumnInfo
    @NonNull
    public int user_ID;
    @ColumnInfo
    @NonNull
    public String gioco_name;
    @ColumnInfo
    @NonNull
    public int fiera_id;


    @ColumnInfo
    public boolean returned;


    @ColumnInfo (defaultValue =  "CURRENT_TIMESTAMP")
    @NonNull
    public String created_at;
}
