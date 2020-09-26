package com.fourstooges.quickclips.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.fourstooges.quickclips.MainActivity;
import com.fourstooges.quickclips.R;
import com.fourstooges.quickclips.database.ClipItems;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClipEditActivity extends AppCompatActivity {
    private ClipItems.ClipItem clipItem;
    private EditText tfTitle, tfBody;
    private CheckBox cbPublic;
    private FirebaseAuth mAuth;

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
        cbPublic = findViewById(R.id.check_box_public);
        cbPublic.setEnabled(clipItem.isPublic());
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.clip_edit_menu, menu);
        return true;
    }

    public void save(MenuItem item) {
        String title = tfTitle.getText().toString();
        String text = tfBody.getText().toString();
        boolean isPublic = cbPublic.isEnabled();
        clipItem.setTitle(title);
        clipItem.setText(text);
        clipItem.setPublic(isPublic);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref = ref.child("Users").child(mAuth.getCurrentUser().getUid()).child("quickclips");
        Task<Void> task = ref.child(clipItem.getId()).setValue(clipItem);
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
//        MainActivity.getClipRecyclerView().getAdapter().notifyDataSetChanged();
        finish();
    }
}