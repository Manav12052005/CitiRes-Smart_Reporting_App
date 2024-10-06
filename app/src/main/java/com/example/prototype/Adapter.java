package com.example.prototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> modelArrayList;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(null);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.headlines.setText(modelArrayList.get(position).getTitle());
         holder.mainnews.setText(modelArrayList.get(position).getDescription());
         holder.author.setText(modelArrayList.get(position).getAuthor());
         holder.publishdate.setText(modelArrayList.get(position).getPublishedAt());
         Glide.with(context).load(modelArrayList.get(position).getUrlToImage()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {

        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView headlines, mainnews,author,publishdate;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            headlines = itemView.findViewById(R.id.main_news_headline);
            mainnews =  itemView.findViewById(R.id.text_view_left);
            author =  itemView.findViewById(R.id.author_id);
            publishdate =  itemView.findViewById(R.id.published_id);
            imageView =  itemView.findViewById(R.id.news_image);
        }
    }
}

