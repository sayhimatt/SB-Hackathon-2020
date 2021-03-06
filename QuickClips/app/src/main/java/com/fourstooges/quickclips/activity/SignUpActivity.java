package com.fourstooges.quickclips.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

import static com.fourstooges.quickclips.R.drawable.rectangle_field_green;
import static com.fourstooges.quickclips.R.drawable.rectangle_field_red;


public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText tfEmail, tfPassword, tfConfirmPassword;
    private Button btSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        tfEmail = findViewById(R.id.edit_text_email);
        tfPassword = findViewById(R.id.edit_text_password);
        tfConfirmPassword = findViewById(R.id.edit_confirm_text_password);
        btSignUp = findViewById(R.id.bt_sign_up);
        tfPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    tfConfirmPassword.setEnabled(false);
                } else {
                    tfConfirmPassword.setEnabled(true);
                }
            }
        });
        tfConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.equals(s, tfPassword.getText())) {
                    btSignUp.setEnabled(true);
                    tfPassword.setBackground(ContextCompat.getDrawable(SignUpActivity.this, rectangle_field_green));
                    tfConfirmPassword.setBackground(ContextCompat.getDrawable(SignUpActivity.this, rectangle_field_green));
                } else {
                    btSignUp.setEnabled(false);
                    tfPassword.setBackground(ContextCompat.getDrawable(SignUpActivity.this, rectangle_field_red));
                    tfConfirmPassword.setBackground(ContextCompat.getDrawable(SignUpActivity.this, rectangle_field_red));
                }
            }
        });
    }

    public void signUp(View v) {
        String email = tfEmail.getText().toString();
        String password = tfPassword.getText().toString();
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