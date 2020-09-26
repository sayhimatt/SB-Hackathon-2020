package com.fourstooges.quickclips.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fourstooges.quickclips.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.guidi.myapplication.R;

public class SignInActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient gsoClient;
    private FirebaseAuth mAuth;
    private EditText tfEmail, tfPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        createRequest();
    }

    public void initControls() {
        tfEmail = findViewById(R.id.tf_email);
        tfPassword = findViewById(R.id.tf_password);
    }

    public void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gsoClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            Toast.makeText(SignInActivity.this, "Welcome " + currentUser.getDisplayName() + "!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            String currentUserID = mAuth.getCurrentUser().getUid();
            intent.putExtra("userID", currentUserID);
            setResult(RESULT_OK, intent);
            startActivity(intent);
            finish();
        }
    }

    public void sign_in(View v) {
        String email = ((EditText) findViewById(R.id.tf_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.tf_password)).getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Fill In The Empty Fields", Toast.LENGTH_LONG).show();
        } else { // Proceed to login
            System.out.println(email);
            System.out.println(password);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        Toast.makeText(SignInActivity.this, "Welcome " + currentUser.getDisplayName() + "!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        String currentUserID = currentUser.getUid();
                        intent.putExtra("userID", currentUserID);
                        setResult(RESULT_OK, intent);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignInActivity.this, "Sign-In Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void sign_up(View v) {
        String email = ((EditText) findViewById(R.id.tf_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.tf_password)).getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Fill In The Empty Fields", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignInActivity.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        String currentUserID = mAuth.getCurrentUser().getUid();
                        intent.putExtra("userID", currentUserID);
                        setResult(RESULT_OK, intent);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignInActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void signInWithGoogle(View v) {
        Intent intent = gsoClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    Toast.makeText(SignInActivity.this, "Welcome " + currentUser.getDisplayName() + "!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    String currentUserID = currentUser.getUid();
                    intent.putExtra("userID", currentUserID);
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                    finish();
                } else { // Failed to login
                    Toast.makeText(getBaseContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}