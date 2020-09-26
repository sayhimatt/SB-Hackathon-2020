package com.fourstooges.quickclips;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class ClipDialogBuilder extends AlertDialog.Builder {
    private View view;

    public ClipDialogBuilder(Context context) {
        super(context);
        view = View.inflate(context, R.layout.dialog_add_clip, null);
        super.setView(view);
    }
}
