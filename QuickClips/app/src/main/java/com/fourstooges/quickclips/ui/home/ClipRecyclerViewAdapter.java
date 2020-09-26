package com.fourstooges.quickclips.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fourstooges.quickclips.R;
import com.fourstooges.quickclips.database.ClipItems;
import com.google.firebase.auth.FirebaseAuth;


import java.util.List;

public class ClipRecyclerViewAdapter extends RecyclerView.Adapter<ClipRecyclerViewAdapter.ViewHolder>{
    private  List<ClipItems.ClipItem> mValues;
    private HomeFragment homeFragment;
    private Context c;
    private FirebaseAuth mAuth;
    private String currentUserID;

    public ClipRecyclerViewAdapter(List<ClipItems.ClipItem> items, HomeFragment homeFragment, Context c){
        mValues = items;
        this.homeFragment = homeFragment;
        this.c = c;
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clip_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
            public ClipItems.ClipItem cItem;
            public Button modify_b;
            public Button copy_b;
            public TextView title_tv;
            public TextView main_tv;

        public ViewHolder(View view) {
            super(view);
            modify_b= view.findViewById(R.id.modify_b);
            copy_b= view.findViewById(R.id.copy_b);
            title_tv=view.findViewById(R.id.title_tv);
            main_tv=view.findViewById(R.id.mText_tv);
        }

        /**
         * @return the plans current subject is printed.
         */
        @Override
        @NonNull
        public String toString() {
            return super.toString() ;
        }
    }
}
