package com.example.pokepedia.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.pokepedia.model.Sprite;
import com.example.pokepedia.restapi.PokemonRestAPI;
import com.example.pokepedia.R;
import com.example.pokepedia.model.Pokemon;
import com.example.pokepedia.model.RestPokemonResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    static final String BASE_URL = "https://pokeapi.co/api/v2/";

    private static final String PREFS = "PREFS";
    private static final String PREFS_NAME = "PREFS_NAME";
    private static final String PREFS_ID = "PREFS_ID";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



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



        downloadList();
    }

    public void downloadList() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokemonRestAPI pokemonAPI = retrofit.create(PokemonRestAPI.class);

        Call<RestPokemonResponse> call = pokemonAPI.getListPokemon();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                RestPokemonResponse restPokemonResponse = response.body();
                List<Pokemon> listPokemon = restPokemonResponse.getResults();
                showList(listPokemon);
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                Log.d("ERREUR", "API K.O.");
            }
        });
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

}