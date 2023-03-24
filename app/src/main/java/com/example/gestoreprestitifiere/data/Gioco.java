package com.example.gestoreprestitifiere.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "gioco")
public class Gioco {
    @PrimaryKey
    @NonNull
    private String nome;
    @ColumnInfo(name = "min_giocatori")
    private int minGiocatori;
    @ColumnInfo(name = "max_giocatori")
    private int maxGiocatori;

    @Ignore
    public Gioco(@NonNull String nome) {
        this.nome = nome;
    }

    public Gioco(@NonNull String nome, int minGiocatori, int maxGiocatori) {
        this.nome = nome;
        this.minGiocatori = minGiocatori;
        this.maxGiocatori = maxGiocatori;

    }


    public String getNome() {return nome;}
    public int getMinGiocatori() {return minGiocatori;}
    public int getMaxGiocatori() {return maxGiocatori;}

    public void setMinGiocatori(int min){minGiocatori = min;}
    public void setMaxGiocatori(int max){maxGiocatori = max;}


    public String toString() {
        return nome;
    }

}
