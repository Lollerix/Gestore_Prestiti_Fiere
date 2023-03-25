package com.example.gestoreprestitifiere.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Area {
    @PrimaryKey (autoGenerate = true)
    public int code;
    @ColumnInfo
    public String name;
}
