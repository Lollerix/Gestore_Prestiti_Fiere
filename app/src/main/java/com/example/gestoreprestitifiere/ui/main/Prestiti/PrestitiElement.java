package com.example.gestoreprestitifiere.ui.main.Prestiti;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestoreprestitifiere.R;

public class PrestitiElement extends RecyclerView.ViewHolder {
    private final TextView textView;

    public PrestitiElement(@NonNull View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.giocoView);
    }

    public TextView getTextView() {
        return textView;
    }
}
