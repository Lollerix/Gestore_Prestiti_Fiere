package com.example.gestoreprestitifiere.ui.main.users;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class UserDialog extends Dialog {
    private Context mContext;

    public UserDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

}
