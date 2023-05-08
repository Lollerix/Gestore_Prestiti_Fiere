package com.example.gestoreprestitifiere.ui.main.users;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestoreprestitifiere.R;
import com.example.gestoreprestitifiere.data.User;

import java.util.List;

public class UserElement extends RecyclerView.ViewHolder {

    private UserListAdapter adapterRef;
    private TextView name;
    private TextView tel;
    private int age;
    private Button modify;


    public UserElement(@NonNull View itemView) {
        super(itemView);
        adapterRef = null;
        name = (TextView) itemView.findViewById(R.id.userItemName);
        tel = (TextView) itemView.findViewById(R.id.userItemTel);
        modify = (Button) itemView.findViewById(R.id.userItemModify);

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               User user = new User(name.getText().toString(), tel.getText().toString(), age);
                ModifyUserDialog clienti = new ModifyUserDialog(v.getContext(), user, adapterRef);
                clienti.show();
            }
        });

    }

    public TextView getName() {return name;}
    public TextView getTel() {return tel;}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAdapterRef(UserListAdapter adapterRef) {
        this.adapterRef = adapterRef;
    }
}
