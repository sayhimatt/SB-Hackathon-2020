package com.guidi.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.guidi.myapplication.R;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 111;
    private GoogleSignInClient gsoClient; // Adriana is taking too long
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean matt = false;
        if(matt) {
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
        }else {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            auth = FirebaseAuth.getInstance();
        }
    }

    public void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gsoClient = GoogleSignIn.getClient(this, gso);
    }



    public void sign_up(View v) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void signInWithGoogle(View v) {
        Intent intent = gsoClient.getSignInIntent();
        startActivityForResult(intent,0);
    }
}