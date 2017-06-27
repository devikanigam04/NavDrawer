package com.app.navdrawer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.navdrawer.R;
import com.app.navdrawer.base.BackButtonSupportFragment;
import com.app.navdrawer.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class First extends BaseFragment implements BackButtonSupportFragment {

    private Toast toast;

    public static First newInstance() {
        return new First();
    }

    private boolean consumingBackPress = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button)
    void btnClick() {
        add(Detail.newInstance(), true);
    }

    @Override
    protected String getTitle() {
        return "First";
    }

    @Override
    public boolean onBackPressed() {
        //return true when handled by yourself
        if (consumingBackPress) {
            //This is actually a terrible thing to do and totally against the guidelines
            // Ideally you shouldn't handle the backpress ever, so really think twice about what
            // you are doing and whether you are getting hacky
            toast = Toast.makeText(getActivity(), "Press back once more to quit the application", Toast.LENGTH_LONG);
            toast.show();
            consumingBackPress = false;
            return true; //consumed
        }
        toast.cancel();
        return false; //delegated
    }
}
