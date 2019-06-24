package com.example.ntp_projekt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public class RecyclerViewAdapterSt extends RecyclerView.Adapter<RecyclerViewAdapterSt.ViewHolder>{
    private ArrayList<String> ids;
    protected ArrayList<String>  index_nrs;
    private LinkedList<String> chosen_nrs = new LinkedList<String>();
    int a;
    Context c;

    public LinkedList<String> getChosen_nrs() {
        return chosen_nrs;
    }
    public RecyclerViewAdapterSt(ArrayList<String> ids,ArrayList<String> index_nrs) {
        this.ids = ids;
        this.index_nrs = index_nrs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_st,viewGroup,false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.index_nr.setText(index_nrs.get(i));
        viewHolder.parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(chosen_nrs.contains(ids.get(i))){
                    chosen_nrs.remove(ids.get(i));
                    viewHolder.parent.setBackgroundColor(Color.WHITE);
                }
                else{
                    chosen_nrs.push(ids.get(i));
                    viewHolder.parent.setBackgroundColor(Color.LTGRAY);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return ids.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView index_nr;
        RelativeLayout parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            index_nr = itemView.findViewById(R.id.textView9);
            parent = itemView.findViewById(R.id.parent_layout2);
        }
    }
}
