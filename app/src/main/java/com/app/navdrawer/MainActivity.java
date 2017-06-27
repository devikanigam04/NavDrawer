package com.app.navdrawer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.app.navdrawer.base.BaseActivity;
import com.app.navdrawer.fragment.First;
import com.app.navdrawer.fragment.Second;
import com.app.navdrawer.fragment.Third;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.main_drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setNavigationView();
        setDrawerAndToggle();
        add(First.newInstance(), false);
    }

    private void setNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setDrawerAndToggle() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerToggle.setDrawerIndicatorEnabled(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public ActionBarDrawerToggle getDrawerToggle() {
        return drawerToggle;
    }

    @Override
    public DrawerLayout getDrawer() {
        return drawerLayout;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.first:
                if (!getTitle().equals("Detail"))
                    refresh();
                break;
            case R.id.second:
                add(Second.newInstance(), false);
                break;
            case R.id.third:
                add(Third.newInstance(), false);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void refresh() {
        if (getSupportFragmentManager().getBackStackEntryCount() >= 1)
            getSupportFragmentManager().popBackStack();
        else
            add(First.newInstance(), false);
    }
}
