package com.fourstooges.quickclips.ui.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fourstooges.quickclips.R;
import com.fourstooges.quickclips.activity.ClipEditActivity;
import com.fourstooges.quickclips.database.ClipItems;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.List;

public class ClipRecyclerViewAdapter extends RecyclerView.Adapter<ClipRecyclerViewAdapter.ViewHolder> {
    private List<ClipItems.ClipItem> mValues;
    private Context c;
    private boolean flag;
    private FirebaseAuth mAuth;

    public ClipRecyclerViewAdapter(List<ClipItems.ClipItem> items, Context c) {
        mValues = items;
        this.c = c;
        mAuth = FirebaseAuth.getInstance();
    }

    public ClipRecyclerViewAdapter(List<ClipItems.ClipItem> items, Context c, boolean flag) {
        mValues = items;
        this.c = c;
        this.flag = flag; // if flag is true, then hide the buttons
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clip_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (flag) {
            holder.btDelete.setVisibility(View.INVISIBLE);
            holder.btCopy.setVisibility(View.INVISIBLE);
            holder.cbPublic.setVisibility(View.INVISIBLE);
        } else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipItems.ClipItem item = mValues.get(position);
                    Intent i = new Intent(c, ClipEditActivity.class);
                    i.putExtra("clip_item", item);
                    c.startActivity(i);
                }
            });
            holder.btDelete.setAnimation(AnimationUtils.loadAnimation(c, R.anim.anim_fade_trans_1));
            holder.btCopy.setAnimation(AnimationUtils.loadAnimation(c, R.anim.anim_fade_trans_1));
            holder.cbPublic.setAnimation(AnimationUtils.loadAnimation(c, R.anim.anim_fade_trans_1));
        }
        holder.tvTitle.setText(mValues.get(position).getTitle());
        holder.tvText.setText(mValues.get(position).getText());
        holder.cbPublic.setChecked(mValues.get(position).isPublic());
        System.out.println("id=" + mValues.get(position).getId());
        // Apply Animations
//        holder.modify_b.setAnimation(AnimationUtils.loadAnimation(c, R.anim.anim_fade_trans_1));

        holder.tvTitle.setAnimation(AnimationUtils.loadAnimation(c, R.anim.anim_fade_trans_1));
        holder.tvText.setAnimation(AnimationUtils.loadAnimation(c, R.anim.anim_fade_trans_1));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(), "Copied!", Toast.LENGTH_SHORT).show();
                String text = holder.tvText.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setPrimaryClip(ClipData.newPlainText("", text));
                return true;
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clipId = mValues.get(position).getId();
                String userId = mAuth.getCurrentUser().getUid();
                DatabaseReference ref = FirebaseDatabase.getInstance()
                        .getReference().child("Users")
                        .child(userId).child(clipId);
                System.out.println(ref);
                System.out.println(ref.getKey());
                Task<Void> task = ref.removeValue();
                Toast.makeText(view.getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                ClipItems.removeItem(position);
                ClipRecyclerViewAdapter.this.notifyDataSetChanged();
            }
        });

        holder.btCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Copied!", Toast.LENGTH_SHORT).show();
                String text = holder.tvText.getText().toString();
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
        public ImageButton btCopy, btDelete;
        public TextView tvTitle;
        public TextView tvText;
        public CheckBox cbPublic;

        public ViewHolder(View view) {
            super(view);
//            modify_b= view.findViewById(R.id.modify_b);
            btDelete = view.findViewById(R.id.bt_delete);
            btCopy = view.findViewById(R.id.bt_copy);
            tvTitle = view.findViewById(R.id.title_tv);
            tvText = view.findViewById(R.id.mText_tv);
            cbPublic = view.findViewById(R.id.cb_public);
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
