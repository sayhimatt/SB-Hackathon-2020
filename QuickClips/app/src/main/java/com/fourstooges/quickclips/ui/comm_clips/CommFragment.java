package com.fourstooges.quickclips.ui.comm_clips;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fourstooges.quickclips.R;
import com.fourstooges.quickclips.database.ClipItems;
import com.fourstooges.quickclips.ui.home.ClipRecyclerViewAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;


public class CommFragment extends Fragment {
    private View root;
    private EditText tfKey;
    private ImageButton btSearch;
    private RecyclerView rv;
    private List<ClipItems.ClipItem> clipItems;
    private ClipRecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_comm, container, false);
        load();
        return root;
    }

    public void load() {
        rv = root.findViewById(R.id.clip_list);
        clipItems = new LinkedList<>();
        adapter = new ClipRecyclerViewAdapter(clipItems, getContext(), true);
        rv.setAdapter(adapter);
        tfKey = root.findViewById(R.id.comm_search_et);
        btSearch = root.findViewById(R.id.comm_search_ib);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clipItems.clear();
                adapter.notifyDataSetChanged();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
                Query q = ref.orderByKey();

                q.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                int i = 0;
                                for (DataSnapshot innerSnapShot : snapshot.getChildren()) {
                                    if ((Boolean) innerSnapShot.child("public").getValue() == true) {
                                        if (innerSnapShot.child("title").getValue().toString().toLowerCase().contains(tfKey.getText().toString().toLowerCase())) {
                                            String title = innerSnapShot.child("title").getValue().toString();
                                            String cat = innerSnapShot.child("category").getValue().toString();
                                            String text = innerSnapShot.child("text").getValue().toString();


                                            ClipItems.ClipItem c = new ClipItems.ClipItem(title, cat, text, true);
                                            clipItems.add(c);
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                }

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}