package com.fourstooges.quickclips.ui.comm_clips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fourstooges.quickclips.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class CommFragment extends Fragment {
    private View root;
    private EditText tfKey;
    private ImageButton btSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_comm, container, false);
        load();
        return root;
    }

    public void load() {
        tfKey = root.findViewById(R.id.comm_search_et);
        btSearch = root.findViewById(R.id.comm_search_ib);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Searching...");
                String key = tfKey.getText().toString();
                Query query = FirebaseDatabase.getInstance().getReference().child("quickclips")
                        .orderByChild("title").equalTo(key);
            }
        });
    }
}