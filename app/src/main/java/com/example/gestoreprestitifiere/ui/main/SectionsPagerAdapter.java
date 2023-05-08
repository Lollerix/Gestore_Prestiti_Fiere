package com.example.gestoreprestitifiere.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gestoreprestitifiere.R;
import com.example.gestoreprestitifiere.ui.main.prestiti.PrestitiFragment;
import com.example.gestoreprestitifiere.ui.main.prestiti.PrestitiTornatiFragment;
import com.example.gestoreprestitifiere.ui.main.users.UserFragment;

import java.util.ArrayList;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        fragmentList.add(new PrestitiFragment(mContext));
        fragmentList.add(new PrestitiTornatiFragment(mContext));
        fragmentList.add(new UserFragment(mContext));
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }

    public void refreshUsers() {
        UserFragment uf = (UserFragment) fragmentList.get(2);
        uf.initDataset();
    }
}