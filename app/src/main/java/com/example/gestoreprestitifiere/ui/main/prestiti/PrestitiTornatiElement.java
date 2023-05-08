package com.example.gestoreprestitifiere.ui.main.prestiti;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestoreprestitifiere.MainActivity;
import com.example.gestoreprestitifiere.R;
import com.example.gestoreprestitifiere.data.Prestito;
import com.example.gestoreprestitifiere.data.PrestitoDao;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class PrestitiTornatiElement extends RecyclerView.ViewHolder {
    private final TextView giocoView;
    private final TextView userView;
    private final TextView oraView;
    private final TextView oraRetView;
    private int id;
    private PrestitiTornatiListAdapter adapterRef;

    public PrestitiTornatiElement(@NonNull View itemView) {
        super(itemView);

        giocoView = (TextView) itemView.findViewById(R.id.giocoRitornoView);
        userView = (TextView) itemView.findViewById(R.id.userRitornoView);
        oraView = (TextView) itemView.findViewById(R.id.oraUscitaView);
        oraRetView = (TextView) itemView.findViewById(R.id.oraRitornoView);

    }

    public TextView getOraRetView() {
        return oraRetView;
    }

    public TextView getGiocoView() {
        return giocoView;
    }

    public TextView getUserView() {return userView;}

    public TextView getOraView() {
        return oraView;
    }

    public void setAdapterRef(PrestitiTornatiListAdapter adapterRef) {
        this.adapterRef = adapterRef;
    }

    public void setId(int id) {
        this.id = id;
    }
}
