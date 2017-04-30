package com.ninja.emilior.movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView mTitleText;
    private TextView mReleaseDate;
    private TextView mSummary;
    private ImageView mImage;
    private RatingBar mRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitleText = (TextView) findViewById(R.id.detailTitle);
        mReleaseDate = (TextView) findViewById(R.id.detailRelease);
        mSummary = (TextView) findViewById(R.id.detailSummary);
        mImage = (ImageView) findViewById(R.id.detailImage);
        mRating = (RatingBar) findViewById(R.id.detailRatingBar);

        Intent intent = getIntent();
        Context context = getApplicationContext();
        String json = intent.getStringExtra("JSON");
        Gson gson = new Gson();
        Movie movie = gson.fromJson(json, Movie.class);

        mTitleText.setText(movie.getTitle());
        mSummary.setText(movie.getOverview());
        mReleaseDate.setText("Released on: "+movie.getRelease_date());
        Picasso.with(context)
                .load(movie.getPoster_path()).into(mImage);

        float rating = (float)movie.getVote_average()/2;

        mRating.setRating(rating);

    }
}
