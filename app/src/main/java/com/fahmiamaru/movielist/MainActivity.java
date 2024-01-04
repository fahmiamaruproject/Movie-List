package com.fahmiamaru.movielist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.fahmiamaru.movielist.adapter.MovieAdapter;
import com.fahmiamaru.movielist.model.Movie;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //api=https://api.themoviedb.org/3/movie/popular?api_key=fed3065d290f59dee0b0433bc6ee9e39

    RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyecler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        requestQueue = VolleySingleton.getmInstance(getApplicationContext()).getRequestQueue();
        movieList = new ArrayList<>();
        fetchMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.like:
                startActivity(new Intent(getApplicationContext(), LikeActivity.class));
                return true;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;

            case R.id.bahasa:
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                return true;

            case R.id.about:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fetchMovies() {
        String url = getString(R.string.link);
        String baseposter = "https://themoviedb.org/t/p/w500";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String title = jsonObject1.getString("title");
                    String overview = jsonObject1.getString("overview");
                    String poster = baseposter + jsonObject1.getString("poster_path");
                    String release = jsonObject1.getString("release_date");

                    Movie movie = new Movie(title , poster , overview , release);
                    movieList.add(movie);
                }
                MovieAdapter adapter = new MovieAdapter(getApplicationContext() , movieList);
                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });
        requestQueue.add(stringRequest);
    }
}