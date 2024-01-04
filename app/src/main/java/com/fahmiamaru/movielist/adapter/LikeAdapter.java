package com.fahmiamaru.movielist.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fahmiamaru.movielist.DetailActivity;
import com.fahmiamaru.movielist.R;
import com.fahmiamaru.movielist.model.Like;

import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.LikeHolder> {

    Context context;
    List<Like> movieList;

    public LikeAdapter(Context context, List<Like> movieList) {
        this.context = context;
        this.movieList = movieList;
    }


    @NonNull
    @Override
    public LikeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_movie,parent,false);
        return new LikeAdapter.LikeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeHolder holder, int position) {
        Like movie = movieList.get(position);
        holder.release.setText(movie.getRelease());
        holder.title.setText(movie.getTitle());
        holder.overview.setText(movie.getOverview());
        Glide.with(context).load(movie.getPoster()).into(holder.imageView);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title" , movie.getTitle());
                bundle.putString("overview" , movie.getOverview());
                bundle.putString("poster" , movie.getPoster());
                bundle.putString("release" , movie.getRelease());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class LikeHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title , overview , release;
        RelativeLayout relativeLayout;

        public LikeHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title_tv);
            overview = itemView.findViewById(R.id.overview_tv);
            release = itemView.findViewById(R.id.release);
            relativeLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
