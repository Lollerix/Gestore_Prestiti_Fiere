package com.example.gestoreprestitifiere.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private final Integer id;

    @ColumnInfo
    @NonNull
    private final String nome;
    @ColumnInfo
    @NonNull
    private final String telefono;
    @ColumnInfo
    private final int age;
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    private final String created_time;

    @Ignore
    public User(@NonNull String nome, @NonNull String telefono) {
        this.nome = nome;
        this.telefono = telefono;
        this.age = 0;
        this.id = 0;
        this.created_time = null;
    }

    public User(@NonNull Integer id, @NonNull String nome, @NonNull String telefono, int age, String created_time) {
        this.id = id;
        this.nome = nome;
        this.telefono = telefono;
        this.age = age;
        this.created_time = created_time;
    }

    @NonNull
    public String getNome(){
        return nome;
    }
    @NonNull
    public String getTelefono() {return telefono;}

    public int getAge() {
        return age;
    }

    public String getCreated_time() {
        return created_time;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String toString() {
        return nome;
    }
}
