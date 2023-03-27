package com.example.gestoreprestitifiere.ui.main.Prestiti;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestoreprestitifiere.R;
import com.example.gestoreprestitifiere.data.GiocoUser;

public class PrestitiListAdapter extends RecyclerView.Adapter<PrestitiElement> {

    private GiocoUser[] localDataSet;

    public PrestitiListAdapter(GiocoUser[] dataset) {
        localDataSet = dataset;
    }




    @NonNull
    @Override
    public PrestitiElement onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prestito_list_element, parent, false);
        return new PrestitiElement(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrestitiElement holder, int position) {
        holder.getTextView().setText(localDataSet[position].gioco_name);
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
