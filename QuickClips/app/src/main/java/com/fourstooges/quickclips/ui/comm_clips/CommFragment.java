package com.fourstooges.quickclips.ui.comm_clips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fourstooges.quickclips.R;


public class CommFragment extends Fragment {
    private View root;
    private Button btSearch;

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

            }
        });
    }
}