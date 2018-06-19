package com.homecredit.ph.recyclerviewactivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ListItem item = (ListItem) view.getTag();

            Context context = view.getContext();
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("imageUrl", item.getImageUrl());
            intent.putExtra("name", item.getHead());
            context.startActivity(intent);
        }
    };

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.textViewDesc.setText(listItem.getDesc());
        holder.textViewHead.setText(listItem.getHead());

        Picasso.get()
                .load(listItem.getImageUrl())
                .into(holder.imageView);

        holder.itemView.setTag(listItem);
        holder.itemView.setOnClickListener(mOnClickListener);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDescription);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
