package com.example.vitaApp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vitaApp.R;
import com.example.vitaApp.interfaces.CropsItemClickListener;


public class EyeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView name;
    public ImageView image;
    public CropsItemClickListener listener;
    private final Context context;

    public EyeViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        name = (TextView)itemView.findViewById(R.id.cropName);
        image = (ImageView)itemView.findViewById(R.id.cropImage);
    }

    public void setItemClickListener(CropsItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(),false);
    }
}
