package com.fahmiamaru.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import com.fahmiamaru.uas.adapter.LikeAdapter;
import com.fahmiamaru.uas.model.Like;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class LikeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<Like> movieList;
    private DB_Helper helper;
    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyecler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        movieList = new ArrayList<>();

        helper = new DB_Helper(getBaseContext());
        fetchlike(mUser.getUid());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchlike(String user){
        SQLiteDatabase ReadData = helper.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM movie where user =?;", new String[]{user});
        cursor.moveToFirst();

        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);


            movieList.add(new Like(cursor.getString(1),
                    cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5)));
        }
        LikeAdapter adapter = new LikeAdapter(getApplicationContext() , movieList);
        recyclerView.setAdapter(adapter);
    }
}