package com.fourstooges.quickclips;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.fourstooges.quickclips.database.ClipItems;

public class ClipDialogUtil {
    public static void displayClipItem(Context c, ClipItems.ClipItem item) {
        View view = View.inflate(c, R.layout.dialog_display_clip, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(item.getTitle());
        builder.setView(view);
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
