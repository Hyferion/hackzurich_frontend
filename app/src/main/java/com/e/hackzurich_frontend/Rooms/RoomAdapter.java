package com.e.hackzurich_frontend.Rooms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e.hackzurich_frontend.MainActivity;
import com.e.hackzurich_frontend.R;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {


    private ArrayList<Room> mDataset;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        private TextView owner;
        private Room room;
        private Context context;
        private SharedPreferences sharedPreferences;

        public MyViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            sharedPreferences = context.getSharedPreferences("meetup", 0);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.title);
            owner = (TextView) itemView.findViewById(R.id.id);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

        }
    }


    public RoomAdapter(ArrayList<Room> myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
    }

    @Override
    public RoomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.room, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);

        MyViewHolder vh = new MyViewHolder(layoutView, mContext);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getName());
        holder.owner.setText(mDataset.get(position).getOwner());
        holder.room = mDataset.get(position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}

