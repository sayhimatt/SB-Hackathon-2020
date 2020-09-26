package com.fourstooges.quickclips.ui.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        holder.title_tv.setText(mValues.get(position).getTitle());
        holder.main_tv.setText(mValues.get(position).getText());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast t = Toast.makeText(view.getContext(), "Copied!", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
                String text = holder.main_tv.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", text);
                clipboard.setPrimaryClip(clip);
                return true;
            }
        });

        holder.copy_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(view.getContext(), "Copied!", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
                String text = holder.main_tv.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", text);
                clipboard.setPrimaryClip(clip);
            }
        });

        holder.copy_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



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
