package com.example.fragmenttab.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenttab.R;

public class JsonViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView nme,discription;
   public CardView cardView;
    public JsonViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.img_rv_list_img);
        nme=itemView.findViewById(R.id.tv_rv_name);
        discription=itemView.findViewById(R.id.tv_rv_discription);
        cardView=itemView.findViewById(R.id.cardview_sublist);
    }
}
