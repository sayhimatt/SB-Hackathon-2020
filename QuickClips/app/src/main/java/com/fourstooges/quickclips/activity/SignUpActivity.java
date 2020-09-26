package com.fourstooges.quickclips.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fourstooges.quickclips.MainActivity;
import com.fourstooges.quickclips.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText tfEmail, tfPassword, tfConfirmPassword;
    private Button btSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        tfEmail = findViewById(R.id.tf_email);
        tfPassword = findViewById(R.id.tf_layout_password);
        tfConfirmPassword = findViewById(R.id.tf_layout_confirm_password);
        tfConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                
            }
        });
        btSignUp = findViewById(R.id.bt_sign_up);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void signUp() {
        String email = ((EditText) findViewById(R.id.tf_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.tf_password)).getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Fill In The Empty Fields", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        String currentUserID = mAuth.getCurrentUser().getUid();
                        intent.putExtra("userID", currentUserID);
                        setResult(RESULT_OK, intent);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}