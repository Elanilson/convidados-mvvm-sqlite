package com.apkdoandroid.convidados.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apkdoandroid.convidados.R;
import com.apkdoandroid.convidados.model.GuestModel;
import com.apkdoandroid.convidados.view.listener.OnlistClick;
import com.apkdoandroid.convidados.view.viewholder.GuestViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestViewHolder> {
    private List<GuestModel> mList  = new ArrayList<>();
    private OnlistClick onlistClick;

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_quest_row,parent,false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder holder, int position) {
        holder.bind(mList.get(position),onlistClick);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void attachList(List<GuestModel> list){
        mList = list;
        notifyDataSetChanged();
    }

    public void attachListener(OnlistClick onlistClick){
        this.onlistClick = onlistClick;
    }
}
