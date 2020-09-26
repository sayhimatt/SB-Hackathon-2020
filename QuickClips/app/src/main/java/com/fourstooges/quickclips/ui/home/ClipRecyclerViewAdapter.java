package com.fourstooges.quickclips.ui.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fourstooges.quickclips.R;
import com.fourstooges.quickclips.activity.ClipEditActivity;
import com.fourstooges.quickclips.database.ClipItems;
import com.google.firebase.auth.FirebaseAuth;


import java.util.List;

public class ClipRecyclerViewAdapter extends RecyclerView.Adapter<ClipRecyclerViewAdapter.ViewHolder> {
    private List<ClipItems.ClipItem> mValues;
    private HomeFragment homeFragment;
    private Context c;
    private FirebaseAuth mAuth;

    public ClipRecyclerViewAdapter(List<ClipItems.ClipItem> items, HomeFragment homeFragment, Context c) {
        mValues = items;
        this.homeFragment = homeFragment;
        this.c = c;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clip_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title_tv.setText(mValues.get(position).getTitle());
        holder.main_tv.setText(mValues.get(position).getText());
        System.out.println("id=" + mValues.get(position).getId());
        // Apply Animations
//        holder.modify_b.setAnimation(AnimationUtils.loadAnimation(c, R.anim.anim_fade_trans_1));
        holder.copy_b.setAnimation(AnimationUtils.loadAnimation(c, R.anim.anim_fade_trans_1));
        holder.title_tv.setAnimation(AnimationUtils.loadAnimation(c, R.anim.anim_fade_trans_1));
        holder.main_tv.setAnimation(AnimationUtils.loadAnimation(c, R.anim.anim_fade_trans_1));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(), "Copied!", Toast.LENGTH_SHORT).show();
                String text = holder.main_tv.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setPrimaryClip(ClipData.newPlainText("", text));
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipItems.ClipItem item = mValues.get(position);
                Intent i = new Intent(c, ClipEditActivity.class);
                i.putExtra("clip_item", item);
                c.startActivity(i);
            }
        });

        holder.delete_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Deleted!", Toast.LENGTH_SHORT).show();

            }
        });

        holder.copy_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Copied!", Toast.LENGTH_SHORT).show();
                String text = holder.main_tv.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", text);
                clipboard.setPrimaryClip(clip);
            }
        });


//        holder.modify_b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //        public ClipItems.ClipItem cItem;
//        public Button modify_b;
        public ImageButton copy_b, delete_b;
        public TextView title_tv;
        public TextView main_tv;

        public ViewHolder(View view) {
            super(view);
//            modify_b= view.findViewById(R.id.modify_b);
            delete_b = view.findViewById(R.id.bt_delete);
            copy_b = view.findViewById(R.id.bt_copy);
            title_tv = view.findViewById(R.id.title_tv);
            main_tv = view.findViewById(R.id.mText_tv);
        }

        /**
         * @return the plans current subject is printed.
         */
        @Override
        @NonNull
        public String toString() {
            return super.toString();
        }
    }
}
