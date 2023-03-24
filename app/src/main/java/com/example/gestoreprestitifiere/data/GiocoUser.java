package com.example.gestoreprestitifiere.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "giochi_prestati", primaryKeys = {"user_name", "gioco_name"})
public class GiocoUser {

    @ColumnInfo(name = "user_name")
    @NonNull
    public String userName;
    @ColumnInfo(name = "gioco_name")
    @NonNull
    public String giocoName;

    @ColumnInfo
    @NonNull
    public String data;
    @ColumnInfo
    @NonNull
    public String ora;
}
