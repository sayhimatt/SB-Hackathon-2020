package com.fourstooges.quickclips.ui.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fourstooges.quickclips.MainActivity;
import com.fourstooges.quickclips.R;
import com.fourstooges.quickclips.database.ClipItems;

import java.util.List;


public class HomeFragment extends Fragment {
    private static RecyclerView clipList;
    private static List<ClipItems.ClipItem> mValues = ClipItems.ITEMS;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if (view.findViewById(R.id.clip_list) instanceof RecyclerView){
            Context context = view.getContext();
            ClipItems.ClipItem a = new ClipItems.ClipItem("This is a test","Research","This is helping us finish the project :3");
            mValues.add(a);
            mValues.add(a);
            mValues.add(a);
            mValues.add(a);

            clipList = (RecyclerView) view.findViewById(R.id.clip_list);
            MainActivity.setClipRecyclerView(clipList);
            clipList.setLayoutManager(new LinearLayoutManager(context));
            clipList.setAdapter(new ClipRecyclerViewAdapter(mValues,new HomeFragment(), view.getContext()));
            clipList.getAdapter().notifyDataSetChanged();
        }

        /*final TextView textView = view.findViewById(R.id.test_sayhimatt_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(v.getContext(), "Copied!", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
                String text = "Matt Says Hi!";
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", text);
                clipboard.setPrimaryClip(clip);
            }
        });*/
        return view;
    }




}