package com.fourstooges.quickclips.ui.comm_clips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fourstooges.quickclips.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CommFragment extends Fragment {
    private View root;
    private ImageButton btSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_comm, container, false);
        loadBtSearch();
        return root;
    }

    public void loadBtSearch() {
        btSearch = root.findViewById(R.id.comm_search_ib);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Searching...");
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("quickclips");
                System.out.println(ref.getKey());
            }
        });
    }
}