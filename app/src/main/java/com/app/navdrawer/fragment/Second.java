package com.app.navdrawer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.navdrawer.R;
import com.app.navdrawer.base.BaseFragment;

public class Second extends BaseFragment {

    public static Second newInstance() {
        return new Second();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    protected String getTitle() {
        return "Second";
    }
}
