package com.fourstooges.quickclips.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fourstooges.quickclips.R;
import com.fourstooges.quickclips.database.ClipItems;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView clipList;
    private List<ClipItems.ClipItem> mValues = ClipItems.ITEMS;
    private FirebaseAuth mAuth;
    private String currentUserID;

    @Override
    public void onResume() {
        retrieveClipItems();
        super.onResume();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if (view.findViewById(R.id.clip_list) instanceof RecyclerView) {
            Context context = view.getContext();

            // AS.
            System.out.println("Getting Clip Items...");
//            retrieveClipItems();
            clipList = view.findViewById(R.id.clip_list);
//            MainActivity.setClipRecyclerView(clipList);
            clipList.setLayoutManager(new LinearLayoutManager(context));
            clipList.setAdapter(new ClipRecyclerViewAdapter(mValues, view.getContext()));
            clipList.getAdapter().notifyDataSetChanged();
        }
        return view;
    }

    private void retrieveClipItems() {
        ClipItems.clear();
        currentUserID = mAuth.getCurrentUser().getUid();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference clipItems = database.child("Users").child(currentUserID);
        clipItems.addListenerForSingleValueEvent(new ValueEventListener() {
            /**
             * Modifies the list based on any data changes detected in the database and updates the recycler view accordingly
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapShot : snapshot.getChildren()) {
                    ClipItems.ClipItem item = singleSnapShot.getValue(ClipItems.ClipItem.class);
                    if (!ClipItems.ITEMS.contains(item)) {
                        ClipItems.addItem(item);
                    }
                }
                clipList.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    ;

}