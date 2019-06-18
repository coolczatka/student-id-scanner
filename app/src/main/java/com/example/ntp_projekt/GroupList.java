package com.example.ntp_projekt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.Gallery;

import java.util.ArrayList;

public class GroupList extends RecyclerViewAdapter {
    String name;
    String temp;
    ViewHolder holder;
    public GroupList(Context c, ArrayList<Integer> pct, ArrayList<String> percentages, ArrayList<String> groups) {
        super(c, pct, percentages, groups);
        name="";
        temp="";
    }
    public String getName(){
        return name;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.bar.setVisibility(View.GONE);
        viewHolder.percentages.setVisibility(View.GONE);
        viewHolder.group.setText(groups.get(i));
        viewHolder.group.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
        viewHolder.parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(name=="") {
                    name = groups.get(i);
                    viewHolder.parent.setBackgroundColor(Color.LTGRAY);
                    holder = viewHolder;
                }
                else if(name==temp){
                    holder.parent.setBackgroundColor(Color.WHITE);
                    name="";
                    holder=viewHolder;
                }
                else{
                    holder.parent.setBackgroundColor(Color.WHITE);
                    viewHolder.parent.setBackgroundColor(Color.LTGRAY);
                    name=groups.get(i);
                    holder=viewHolder;
                }
            }
        });
    }
}
