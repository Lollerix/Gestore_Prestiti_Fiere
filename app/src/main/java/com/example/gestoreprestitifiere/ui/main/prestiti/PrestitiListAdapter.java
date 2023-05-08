package com.example.gestoreprestitifiere.ui.main.prestiti;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestoreprestitifiere.MainActivity;
import com.example.gestoreprestitifiere.R;
import com.example.gestoreprestitifiere.data.Prestito;

import java.util.List;

public class PrestitiListAdapter extends RecyclerView.Adapter<PrestitiElement> {

    private List<Prestito> localDataSet;
    private Context mContext;

    public PrestitiListAdapter(List<Prestito> dataset, Context context) {
        System.out.println(dataset);
        localDataSet = dataset;
        mContext = context;
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
        if(position % 2 == 0)
            holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey));
        else
            holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey2));


        Prestito prestito = localDataSet.get(position);
        holder.getGiocoView().setText(prestito.getGioco_name());
        holder.getUserView().setText(prestito.getUser_name());
        holder.getOraView().setText(prestito.getCreated_at().toString());
        holder.setAdapterRef(this);
        holder.setId(prestito.getId());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public void refreshList() {
        localDataSet = MainActivity.getPrestitoDao().getOccupati();
        notifyDataSetChanged();
    }

}
