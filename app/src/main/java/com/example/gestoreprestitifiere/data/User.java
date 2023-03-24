package com.example.gestoreprestitifiere.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User {
    @PrimaryKey
    @NonNull
    private final String nome;
    @ColumnInfo
    @NonNull
    private final String telefono;

    public User(@NonNull String nome, @NonNull String telefono) {
        this.nome = nome;
        this.telefono = telefono;
    }


    @NonNull
    public String getNome(){
        return nome;
    }
    @NonNull
    public String getTelefono() {return telefono;}

    @NonNull
    public String toString() {
        return nome;
    }
}
