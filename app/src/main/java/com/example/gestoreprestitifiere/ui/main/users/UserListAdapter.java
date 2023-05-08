package com.example.gestoreprestitifiere.ui.main.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestoreprestitifiere.MainActivity;
import com.example.gestoreprestitifiere.R;
import com.example.gestoreprestitifiere.data.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserElement> {

    List<User> localDataSet;
    private Context mContext;

    public UserListAdapter(List<User> dataSet, Context context) {
        localDataSet = dataSet;
        mContext = context;
    }


    @NonNull
    @Override
    public UserElement onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_element, parent, false);

        return new UserElement(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserElement holder, int position) {
        if(position % 2 == 0)
            holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey));
        else
            holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey2));
        User user = localDataSet.get(position);
        holder.getName().setText(user.getNome());
        holder.getTel().setText(user.getTelefono());
        holder.setAge(user.getAge());
        holder.setAdapterRef(this);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public void refreshList() {
        localDataSet = MainActivity.getUserDao().getAll();
        this.notifyDataSetChanged();
    }

}
