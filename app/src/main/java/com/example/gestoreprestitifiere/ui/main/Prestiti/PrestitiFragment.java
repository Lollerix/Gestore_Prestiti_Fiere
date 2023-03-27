package com.example.gestoreprestitifiere.ui.main.Prestiti;

import android.content.Context;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.gestoreprestitifiere.R;

public class PrestitiFragment extends Fragment {
    private ListView list;
    private final Context mContext;
        public PrestitiFragment(Context context) {
            super(R.layout.prestito_list_view);
            mContext = context;

            list = (ListView) getView().findViewById(R.id.List);

            list.setAdapter();

            ;

        }



}
