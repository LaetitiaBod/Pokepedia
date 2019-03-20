package com.example.pokepedia.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pokepedia.R;
import com.example.pokepedia.controller.ListController;
import com.example.pokepedia.model.Pokemon;


import java.util.List;



public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ListController listcontroller;

    private static final String PREFS = "PREFS";
    private static final String PREFS_NAME = "PREFS_NAME";
    private static final String PREFS_ID = "PREFS_ID";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listcontroller = new ListController(this);

        listcontroller.onCreate();

        /*
        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);

        if(sharedPreferences.contains(PREFS_NAME) && sharedPreferences.contains(PREFS_ID)) {
            String name = sharedPreferences.getString(PREFS_NAME, null);
            int id =  sharedPreferences.getInt(PREFS_ID, 0);
        } else {
            sharedPreferences
                    .edit()
                    .putString(PREFS_NAME, "")
                    .putInt(PREFS_ID, 1)
                    .apply();
        }
        */

    }

    public void showList(List<Pokemon> input) {
        recyclerView = findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new PokemonListAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

}