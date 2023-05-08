package com.example.gestoreprestitifiere.ui.main.prestiti;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestoreprestitifiere.MainActivity;
import com.example.gestoreprestitifiere.R;
import com.example.gestoreprestitifiere.data.Prestito;
import com.example.gestoreprestitifiere.data.PrestitoDao;

import java.util.List;

public class PrestitiTornatiFragment extends Fragment {
    private PrestitiTornatiListAdapter mAdapter;
    private Context mContext;
    protected RecyclerView mRecyclerView;
    protected List<Prestito> mDataset;

    public PrestitiTornatiFragment(Context context) {
        mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.refreshList();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.prestito_returned_list_view, container, false);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mDataset = getAllLeases();
        mAdapter = new PrestitiTornatiListAdapter(mDataset, mContext);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        // END_INCLUDE(initializeRecyclerView)


        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataset();
    }


    private void initDataset() {

    }

    private List<Prestito> getAllLeases() {
        PrestitoDao prestiti = MainActivity.getPrestitoDao();
        return prestiti.getTornati();
    }

}
