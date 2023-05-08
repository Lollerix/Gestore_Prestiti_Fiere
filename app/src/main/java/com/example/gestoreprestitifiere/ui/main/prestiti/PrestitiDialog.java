package com.example.gestoreprestitifiere.ui.main.prestiti;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;

import com.example.gestoreprestitifiere.MainActivity;
import com.example.gestoreprestitifiere.R;
import com.example.gestoreprestitifiere.data.Gioco;
import com.example.gestoreprestitifiere.data.GiocoDao;
import com.example.gestoreprestitifiere.data.PrestitoDao;
import com.example.gestoreprestitifiere.data.User;
import com.example.gestoreprestitifiere.data.UserDao;
import com.example.gestoreprestitifiere.ui.main.tokenizer.SpaceTokenizer;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PrestitiDialog extends Dialog {
    private MultiAutoCompleteTextView giocoView;
    private MultiAutoCompleteTextView userView;
    private Button submit;
    private PrestitiListAdapter adapterRef;
    private ArrayList<String> giocoNames = new ArrayList<String>();
    private ArrayList<String> userNames = new ArrayList<String>();

    private static final String[] testingList = new String[] {
            "Catan", "Carcassonne", "Scythe"
    };

    public PrestitiDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.prestito_dialog);
        readGiochi();
        readUsers();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, giocoNames);
        giocoView = findViewById(R.id.giocoAutoComplete);
        giocoView.setAdapter(adapter);
        giocoView.setTokenizer(new SpaceTokenizer());

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, userNames);
        userView = findViewById(R.id.userAutoComplete);
        userView.setAdapter(adapter2);
        userView.setTokenizer(new SpaceTokenizer());

        submit = findViewById(R.id.prestitoSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput(v);
            }
        });

    }

    private void checkInput(View v) {
        String s1 = userView.getText().toString().replaceAll("\\s+","");
        String s2 = giocoView.getText().toString().replaceAll("\\s+","");


        User user = findUser(s1);
        Gioco gioco = findGioco(s2);

        if(user != null && gioco != null) {
            PrestitoDao dao = MainActivity.getPrestitoDao();

            dao.insertPrestito(user.getId(), gioco.getNome(), user.getNome(), MainActivity.fiera.getNome(), Calendar.getInstance().getTime());
            CharSequence text = "Prestito inserito";
            Snackbar.make(v, text, 1200).show();
            adapterRef.refreshList();
            this.cancel();
        }
        else {
            CharSequence text = "Dati errati";
            Snackbar.make(v, text, 1500).show();
        }
    }

    private Gioco findGioco(String name) {
        GiocoDao gD = MainActivity.getGiocoDao();
        List<Gioco> giocoList = gD.getAll();
        for (Gioco g: giocoList) {
            if(name.equals(g.getNome().replaceAll("\\s+","")))
                return g;
        }
        return null;
    }

    private User findUser(String name) {
        UserDao gD = MainActivity.getUserDao();
        List<User> giocoList = gD.getAll();
        for (User user: giocoList) {
            if(name.equals(user.getNome().replaceAll("\\s+","")))
                return user;
        }
        return null;
    }

    private void readGiochi() {
        PrestitoDao gD = MainActivity.getPrestitoDao();
        List<Gioco> giocoList = gD.getFree();
            for (Gioco g: giocoList) {
                giocoNames.add(g.getNome());
            }
    }
    private void readUsers() {
        UserDao gD = MainActivity.getUserDao();
        List<User> giocoList = gD.getAll();
        for (User user: giocoList) {
            userNames.add(user.getNome());
        }
    }

    public void setAdapterRef(PrestitiListAdapter adapterRef) {
        this.adapterRef = adapterRef;
    }
}
