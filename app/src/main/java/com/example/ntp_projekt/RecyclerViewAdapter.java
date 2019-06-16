package com.example.ntp_projekt;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
//4 statistics
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    public static final String TAG = "MY_TAG";
    protected ArrayList<Integer> pct;
    protected ArrayList<String> percentages;
    protected ArrayList<String> groups;
    protected Context c;
    public RecyclerViewAdapter(Context c, ArrayList<Integer> pct, ArrayList<String> percentages, ArrayList<String> groups){
        this.pct = pct;
        this.percentages = percentages;
        this.groups = groups;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_ithem,viewGroup,false);
        ViewHolder v = new ViewHolder(view);
        c = viewGroup.getContext();
        return v;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.bar.setProgress(pct.get(i));
        viewHolder.percentages.setText(percentages.get(i));
        viewHolder.group.setText(groups.get(i));
        viewHolder.parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c,StudentDetailsActivity.class);
                intent.putExtra("group_nr",(String)viewHolder.group.getText());
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProgressBar bar;
        TextView percentages;
        TextView group;
        RelativeLayout parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bar = itemView.findViewById(R.id.progressBar);
            percentages = itemView.findViewById(R.id.name);
            group = itemView.findViewById(R.id.textView);
            parent = itemView.findViewById(R.id.parent_layout);
        }
    }

}
