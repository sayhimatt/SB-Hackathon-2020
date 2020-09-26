package com.fourstooges.quickclips.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.fourstooges.quickclips.R;

public class ClipEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_edit);
        String title = getIntent().getStringExtra("key_title");
        System.out.println(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.clip_edit_menu, menu);
        return true;
    }

    public void itemClicked(MenuItem item){
       
    }
}