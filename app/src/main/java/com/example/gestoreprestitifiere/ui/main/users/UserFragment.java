package com.example.gestoreprestitifiere.ui.main.users;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestoreprestitifiere.MainActivity;
import com.example.gestoreprestitifiere.R;
import com.example.gestoreprestitifiere.data.User;
import com.example.gestoreprestitifiere.data.UserDao;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {

    private UserListAdapter mAdapter;
    private Context mContext;
    protected RecyclerView mRecyclerView;
    protected List<User> mDataset;

    public UserFragment(Context context) {
        mContext = context;


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_list_view, container, false);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.userList);
        // Set CustomAdapter as the adapter for RecyclerView.
        mDataset = getAllUsers();
        mAdapter = new UserListAdapter(mDataset, mContext);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        // END_INCLUDE(initializeRecyclerView)


        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public boolean initDataset() {
        if(mAdapter != null){
            mAdapter.refreshList();
            return true;
        } else{
            return false;
        }
    }

    private List<User> getAllUsers() {
        UserDao users = MainActivity.getUserDao();
        return users.getAll();
    }

    private ArrayList<User> getFakeUsers(){
        ArrayList<User> list = new ArrayList<>();

        list.add(new User("Mario Baldi", "4587548721"));
        list.add(new User("Orbella Cerrico", "4587548721"));
        list.add(new User("Fefferrio Ollisu", "4587548721"));


        return  list;
    }

}
