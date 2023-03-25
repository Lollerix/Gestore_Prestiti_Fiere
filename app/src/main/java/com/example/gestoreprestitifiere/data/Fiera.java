package com.example.gestoreprestitifiere.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Fiera {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private final Integer id;

    @ColumnInfo
    @NonNull
    private final String nome;
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    private final String created_time;

    public Fiera(@NonNull Integer id, @NonNull String nome, String created_time) {
        this.id = id;
        this.nome = nome;
        this.created_time = created_time;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public String getCreated_time() {
        return created_time;
    }
}
