package com.app.navdrawer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentHandler fragmentHandler;

    View.OnClickListener navigationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            fragmentManager.popBackStack();
        }
    };

    FragmentManager.OnBackStackChangedListener onBackStackChangedListener = new FragmentManager
            .OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            onBackStackChangeEvent();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        fragmentHandler = new FragmentHandler(fragmentManager);
        fragmentManager.addOnBackStackChangedListener(onBackStackChangedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDrawer().addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                syncToggleState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                syncToggleState();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        fragmentManager.removeOnBackStackChangedListener(onBackStackChangedListener);
        fragmentManager = null;
        super.onDestroy();
    }

    protected void add(BaseFragment fragment, boolean add) {
        fragmentHandler.add(fragment, add);
    }

    @Override
    public void onBackPressed() {
        if (sendBackToDrawer())
            return;
        if (sendBackToFragment())
            return;
        super.onBackPressed();

        if (fragmentManager.getBackStackEntryCount() == 0)
            finish();
    }

    private boolean sendBackToDrawer() {
        DrawerLayout drawer = getDrawer();
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    private boolean sendBackToFragment() {
        BaseFragment fragment = fragmentHandler.getCurrentFragment();
        if (fragment == null)
            return false;
        if (!(fragment instanceof BackButtonSupportFragment))
            return false;

        return ((BackButtonSupportFragment) fragment).onBackPressed();
    }

    void onBackStackChangeEvent() {
        syncToggleState();
    }

    void syncToggleState() {
        ActionBarDrawerToggle drawerToggle = getDrawerToggle();
        if (drawerToggle == null) {
            return;
        }

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            drawerToggle.setDrawerIndicatorEnabled(false);
            drawerToggle.setToolbarNavigationClickListener(navigationOnClickListener);
            // pop backstack
        } else {
            drawerToggle.setDrawerIndicatorEnabled(true);
            drawerToggle.setToolbarNavigationClickListener(drawerToggle
                    .getToolbarNavigationClickListener());
            // open nav drawer
        }
    }

    public abstract ActionBarDrawerToggle getDrawerToggle();

    public abstract DrawerLayout getDrawer();
}