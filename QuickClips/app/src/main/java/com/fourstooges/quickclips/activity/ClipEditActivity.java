package com.fourstooges.quickclips.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fourstooges.quickclips.R;

public class ClipEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_edit);
        String title = getIntent().getStringExtra("key_title");
        System.out.println(title);
    }
}