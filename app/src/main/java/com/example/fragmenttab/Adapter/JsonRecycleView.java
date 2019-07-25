package com.example.fragmenttab.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fragmenttab.Json.PojoJson;
import com.example.fragmenttab.MainActivity;
import com.example.fragmenttab.R;
import com.example.fragmenttab.viewHolder.JsonViewHolder;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class JsonRecycleView extends RecyclerView.Adapter<JsonViewHolder> {
    ArrayList<PojoJson> ar;
    FragmentActivity fragmentActivity;
    Context cxt;
    public JsonRecycleView(Context cxt, ArrayList<PojoJson> ar, FragmentActivity fragmentActivity) {
    this.ar=ar;
    this.cxt=cxt;
    this.fragmentActivity=fragmentActivity;
    }

    @NonNull
    @Override
    public JsonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(cxt).inflate(R.layout.sublist_element_recycleview,parent,false);
        JsonViewHolder  jsonViewHolder=new JsonViewHolder(view);
        return jsonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final JsonViewHolder holder, final int position) {
        holder.nme.setText(ar.get(position).getName());
        holder.discription.setText(ar.get(position).getDescription().substring(0,100)+"......");
        Glide.with(cxt).load(ar.get(position).getImageUrl()).apply(RequestOptions.circleCropTransform()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity= (MainActivity) fragmentActivity;
                mainActivity.dataSender(ar.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return ar.size();
    }
}
