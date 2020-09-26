package com.fourstooges.quickclips;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.fourstooges.quickclips.activity.SignInActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

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
        inflater.inflate(R.menu.about_menu, menu);
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
                Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                intent.setClass(this, SignInActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showAddClipDialog(MenuItem item) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_clip, null);
        final EditText tfTitle = view.findViewById(R.id.tf_title);
        final EditText tfText = view.findViewById(R.id.tf_text);
        final CheckBox checkBox = view.findViewById(R.id.check_box_public);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle(getString(R.string.add_clip));
        builder.setPositiveButton(R.string.add_clip, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = tfTitle.getText().toString();
                String text = tfText.getText().toString();
                boolean isPublic = checkBox.isActivated();
                addClip(title, text, isPublic);
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void addClip(String title, String text, boolean isPublic) {
        System.out.println("Adding clip");
        System.out.println(title + ", " + text + ", " + isPublic);
        // TODO Your Turn Matt Guidi
    }
}