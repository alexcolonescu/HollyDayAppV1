package com.alex.hollydayappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class SecondActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

      if(actionBarDrawerToggle.onOptionsItemSelected(item)){

          return true;
      }

        return super.onOptionsItemSelected(item);
                    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_Open, R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Log.i("MENU_DRAWER_TAG", "HOME ITEM IS CLICKED");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_search:
                        Log.i("MENU_DRAWER_TAG", "Search ITEM IS CLICKED");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_users:
                        Log.i("MENU_DRAWER_TAG", "Users  ITEM IS CLICKED");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_profile:
                        Log.i("MENU_DRAWER_TAG", "Profiles ITEM IS CLICKED");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_settings:
                        Log.i("MENU_DRAWER_TAG", "Setting ITEM IS CLICKED");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_share:
                        Log.i("MENU_DRAWER_TAG", "Share ITEM IS CLICKED");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_donate:
                        Log.i("MENU_DRAWER_TAG", "Donate ITEM IS CLICKED");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_logout:
                        Log.i("MENU_DRAWER_TAG", "Logout ITEM IS CLICKED");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }


                return true;
            }
        });



    }
}