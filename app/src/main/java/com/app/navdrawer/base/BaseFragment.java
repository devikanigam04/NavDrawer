package com.app.navdrawer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.app.navdrawer.MainActivity;

public abstract class BaseFragment extends Fragment {

    private FragmentHandler fragmentHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentHandler = new FragmentHandler(getActivity().getSupportFragmentManager());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getTitle());
    }

    protected abstract String getTitle();

    protected void add(BaseFragment fragment, boolean add) {
        fragmentHandler.add(fragment, add);
    }
}
