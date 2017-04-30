package com.ninja.emilior.movies;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by emilio on 3/28/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        final View moviesListItem = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.movie_list_items, parent, false);
        return new ViewHolder(moviesListItem);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.titleView.setText(movie.getTitle());
        holder.scoreView.setText(Double.toString(movie.getVote_average()));
        Picasso.with(holder.imageView.getContext())
                .load(movie.getPoster_path()).into(holder.imageView);


        // On click opens new intent to view more info about the Movie.
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                Gson gson = new Gson();
                String json = gson.toJson(movie);
                intent.putExtra("JSON", json);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount(){
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView titleView;
        public TextView scoreView;
        public ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            titleView = (TextView) itemView.findViewById(R.id.titleTextView);
            scoreView = (TextView) itemView.findViewById(R.id.movieScore);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
