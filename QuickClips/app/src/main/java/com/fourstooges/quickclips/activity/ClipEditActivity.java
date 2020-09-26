package com.fourstooges.quickclips.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.fourstooges.quickclips.R;
import com.fourstooges.quickclips.database.ClipItems;

public class ClipEditActivity extends AppCompatActivity {
    private ClipItems.ClipItem clipItem;
    private EditText tfTitle, tfBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_edit);
        clipItem = (ClipItems.ClipItem) getIntent().getSerializableExtra("clip_item");
        System.out.println(clipItem.getId());
        tfTitle = findViewById(R.id.title_ei);
        tfTitle.setText(clipItem.getTitle());
        tfBody = findViewById(R.id.body_ei);
        tfBody.setText(clipItem.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.clip_edit_menu, menu);
        return true;
    }

    public void save(MenuItem item) {
        
    }
}