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
import java.util.List;

public class PrestitiElement extends RecyclerView.ViewHolder {
    private final TextView giocoView;
    private final TextView userView;
    private final TextView oraView;
    private int id;
    private PrestitiListAdapter adapterRef;

    public PrestitiElement(@NonNull View itemView) {
        super(itemView);

        giocoView = (TextView) itemView.findViewById(R.id.giocoView);
        userView = (TextView) itemView.findViewById(R.id.userView);
        oraView = (TextView) itemView.findViewById(R.id.oraView);

        Button restituisci = (Button) itemView.findViewById(R.id.restituisciButton);

        restituisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restituzione(v);
            }
        });
    }

    private void restituzione(View v) {
        PrestitoDao dao = MainActivity.getPrestitoDao();

        Prestito p =  dao.getPrestito(id);

        if(p != null) {
            if(!p.isReturned()) {
                p.setReturned(true);
                p.setReturned_at(Calendar.getInstance().getTime());
                dao.Update(p);
                adapterRef.refreshList();
            }
        } else {
            String s = "Errore nel recuperare il prestito";
            Snackbar.make(v, s, 1800);
        }
    }


    public TextView getGiocoView() {
        return giocoView;
    }

    public TextView getUserView() {return userView;}

    public TextView getOraView() {
        return oraView;
    }

    public void setAdapterRef(PrestitiListAdapter adapterRef) {
        this.adapterRef = adapterRef;
    }

    public void setId(int id) {
        this.id = id;
    }
}
