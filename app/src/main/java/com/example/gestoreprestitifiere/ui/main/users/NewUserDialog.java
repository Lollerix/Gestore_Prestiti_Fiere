package com.example.gestoreprestitifiere.ui.main.users;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.gestoreprestitifiere.MainActivity;
import com.example.gestoreprestitifiere.R;
import com.example.gestoreprestitifiere.data.User;
import com.example.gestoreprestitifiere.data.UserDao;
import com.example.gestoreprestitifiere.ui.main.SectionsPagerAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NewUserDialog extends Dialog{
    private Context mContext;
    private ArrayList<User> users = new ArrayList<>();
    private SectionsPagerAdapter adapterRef;

    public NewUserDialog(@NonNull Context context, SectionsPagerAdapter sectionsPagerAdapter) {
        super(context);
        mContext = context;
        adapterRef = sectionsPagerAdapter;
        setContentView(R.layout.new_user_form);
        readUsers();


        Button btn = findViewById(R.id.new_user_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser(v);
            }
        });

    }

    private void readUsers() {
        UserDao userDao = MainActivity.getUserDao();

        List<User> tmp = userDao.getAll();
        for (User u: tmp) {
            users.add(u);
        }
    }

    private void checkUser(View v) {
        TextView name = findViewById(R.id.editTextNewUserName);
        TextView tel = findViewById(R.id.editTextNumber);
        TextView age = findViewById(R.id.editTextAge);
        String nameString = name.getText().toString();
        String telString = tel.getText().toString();
        String numString = age.getText().toString();
        if( nameString.equals("") || telString.equals("") || numString.equals("")) {
            CharSequence text = "Inserisci tutti i campi";
            Snackbar.make(v, text, 4000).show();
        }
        int ageNum = Integer.parseInt(numString);



        for (User u : users) {
            if(u.getNome().equalsIgnoreCase(nameString) &&
                    u.getTelefono().equalsIgnoreCase(telString)) {
                AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                alertDialog.setTitle("Attenzione");
                alertDialog.setMessage("Utente duplicato");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                this.cancel();
                return;
            }

        }


        insertUser(v, nameString, telString, ageNum);



    }

    private void insertUser(View v, String name, String tel, int age) {
        UserDao userDao = MainActivity.getUserDao();
        userDao.insertUser(name, tel, age);
        CharSequence text = "Utente inserito";
        Snackbar.make(v, text, 1800).show();
        adapterRef.refreshUsers();
        this.cancel();
    }

}
