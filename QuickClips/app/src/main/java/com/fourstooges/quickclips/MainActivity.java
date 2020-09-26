package com.fourstooges.quickclips;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.guidi.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        switch (item.getItemId()) {
            case R.id.about:
                // Need to add description of the app here
                return true;
            case R.id.guidi:
                intent.setData(Uri.parse("https://github.com/sayhimatt"));
                startActivity(intent);
                return true;
            case R.id.campos:
                intent.setData(Uri.parse("https://github.com/Camposm97"));
                startActivity(intent);
                return true;
            case R.id.kleister:
                intent.setData(Uri.parse("https://github.com/kiefuh"));
                startActivity(intent);
                return true;
            case R.id.salomon:
                intent.setData(Uri.parse("https://github.com/adriana-ss"));
                startActivity(intent);
                return true;
            case R.id.sign_out:
                // sign out
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}