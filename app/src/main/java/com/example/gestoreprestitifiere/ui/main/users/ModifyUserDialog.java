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
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ModifyUserDialog extends Dialog {
    private Context mContext;
    private ArrayList<User> users = new ArrayList<>();
    private User modifyMe;
    private UserListAdapter adapterRef;

    public ModifyUserDialog(@NonNull Context context, User user, UserListAdapter adapter) {
        super(context);
        mContext = context;
        adapterRef = adapter;
        setContentView(R.layout.new_user_form);
        readUsers();
        modifyMe = user;

        Button btn = findViewById(R.id.new_user_button);
        TextView name = findViewById(R.id.editTextNewUserName);
        TextView tel = findViewById(R.id.editTextNumber);
        TextView age = findViewById(R.id.editTextAge);

        name.setText(user.getNome() + "");
        tel.setText(user.getTelefono() + "");
        System.out.println(user.getAge() + "Età utente");
        age.setText(user.getAge() + "");

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
        int ageNum = Integer.parseInt(age.getText().toString());


        for (User u : users) {
            if(u.getNome().equalsIgnoreCase(modifyMe.getNome()) &&
                    u.getTelefono().equalsIgnoreCase(modifyMe.getTelefono())) {
                u.setNome(nameString);
                u.setTelefono(telString);
                u.setAge(ageNum);

                MainActivity.getUserDao().updateUsers(u);
                adapterRef.refreshList();
                this.cancel();

                CharSequence text = "Utente modificato";
                Snackbar.make(v, text, 1800).show();
                return;
            }

        }



    }
}
