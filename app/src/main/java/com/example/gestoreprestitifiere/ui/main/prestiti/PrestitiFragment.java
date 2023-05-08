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

public class PrestitiFragment extends Fragment {
    private PrestitiListAdapter mAdapter;
    private Context mContext;
    protected RecyclerView mRecyclerView;
    protected List<Prestito> mDataset;

    public  PrestitiFragment(Context context) {
        mContext = context;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.prestito_list_view, container, false);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mDataset = getAllLeases();
        mAdapter = new PrestitiListAdapter(mDataset, mContext);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        // END_INCLUDE(initializeRecyclerView)


        Button btn_Prestito = (Button) rootView.findViewById(R.id.add_prestito_button);
        btn_Prestito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrestitiDialog dialog = new PrestitiDialog(mContext);
                dialog.setAdapterRef(mAdapter);
                dialog.show();
            }
        });

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
        return prestiti.getOccupati();
    }

}
