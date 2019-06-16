package com.example.ntp_projekt;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentList extends RecyclerViewAdapter {

    public StudentList(Context c, ArrayList<Integer> pct, ArrayList<String> percentages, ArrayList<String> groups) {
        super(c, pct, percentages, groups);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.bar.setProgress(pct.get(i));
        viewHolder.percentages.setText(percentages.get(i));
        viewHolder.group.setText(groups.get(i));
    }
}
