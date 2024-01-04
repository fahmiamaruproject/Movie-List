package com.fahmiamaru.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DetailActivity extends AppCompatActivity {

    ImageView star;
    DB_Helper helper;

    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.poster_image);
        TextView rating_tv = findViewById(R.id.mRelease);
        TextView title_tv = findViewById(R.id.mTitle);
        TextView overview_tv = findViewById(R.id.movervie_tv);

        Bundle bundle = getIntent().getExtras();

        String mTitle = bundle.getString("title");
        String mPoster = bundle.getString("poster");
        String mOverView = bundle.getString("overview");
        String mRating = bundle.getString("release");

        Glide.with(this).load(mPoster).into(imageView);
        rating_tv.setText(mRating);
        title_tv.setText(mTitle);
        overview_tv.setText(mOverView);

        String user = mUser.getUid();
        star = findViewById(R.id.star);

        helper = new DB_Helper(this);

        Boolean ceklike = helper.checkLiked(user,mTitle);

        if (ceklike){
            star.setImageResource(R.drawable.ic_star_rate_on);
        }

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ceklike){
                    Boolean remove = helper.deleteLiked(user,mTitle);
                    if (remove){
                        star.setImageResource(R.drawable.ic_star_rate_off);
                    }
                }else {
                    Boolean insert = helper.insertLiked(user,mTitle,mRating,mOverView,mPoster);
                    if (insert){
                        star.setImageResource(R.drawable.ic_star_rate_on);
                    }
                }
            }
        });
    }
}