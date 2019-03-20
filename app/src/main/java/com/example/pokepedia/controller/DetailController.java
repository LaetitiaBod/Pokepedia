package com.example.pokepedia.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.pokepedia.model.RestIdResponse;
import com.example.pokepedia.model.Sprite;
import com.example.pokepedia.restapi.PokemonRestAPI;
import com.example.pokepedia.view.DetailActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Callback;

public class DetailController {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private DetailActivity activity;
/*
    private static final String PREFS = "_PREFS";
    private static final String PREFS_NAME = "_POKEMON";
    private static final String PREFS_ID = "_ID";
    private static final String PREFS_HEIGH = "_HEIGHT";
    private static final String PREFS_WEIGHT = "_WEIGHT";
    private static final String PREFS_BASE_EXP = "_BASE_EXP";
    private static final String PREFS_SPRITE = "_SPRITE";
    private SharedPreferences sharedPreferences;
*/
    public DetailController(DetailActivity activity) {
        this.activity = activity;
    }

    public void onCreate() {

       /* sharedPreferences = activity.getBaseContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(PREFS_NAME) &&
            sharedPreferences.contains(PREFS_ID) &&
            sharedPreferences.contains(PREFS_HEIGH) &&
            sharedPreferences.contains(PREFS_WEIGHT) &&
            sharedPreferences.contains(PREFS_BASE_EXP) &&
            sharedPreferences.contains(PREFS_SPRITE)) {

            String name = sharedPreferences.getString(PREFS_NAME, null);
            int id = sharedPreferences.getInt(PREFS_ID, 0);
            int height = sharedPreferences.getInt(PREFS_HEIGH, 0);
            int weight = sharedPreferences.getInt(PREFS_WEIGHT, 0);
            int base_exp = sharedPreferences.getInt(PREFS_BASE_EXP, 0);
            String front_default = sharedPreferences.getString(PREFS_SPRITE, null);
            Sprite sprite = new Sprite();
            sprite.setFront_default(front_default);

            activity.showDetails(name, id, height, weight, base_exp, sprite);

        } else {
*/
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            PokemonRestAPI pokemonAPI = retrofit.create(PokemonRestAPI.class);
            int id = activity.getIntent().getIntExtra("key_id", 1);

            Call<RestIdResponse> call = pokemonAPI.getDetailPokemon(id);

            call.enqueue(new Callback<RestIdResponse>() {
                @Override
                public void onResponse(Call<RestIdResponse> call, Response<RestIdResponse> response) {
                    RestIdResponse restIdResponse = response.body();
                    activity.showDetails(restIdResponse.getName(),
                            restIdResponse.getId(),
                            restIdResponse.getHeight(),
                            restIdResponse.getWeight(),
                            restIdResponse.getBase_experience(),
                            restIdResponse.getSprites());
/*
                    sharedPreferences
                            .edit()
                            .putString(PREFS_NAME, restIdResponse.getName())
                            .putInt(PREFS_ID, restIdResponse.getId())
                            .putInt(PREFS_HEIGH, restIdResponse.getHeight())
                            .putInt(PREFS_WEIGHT, restIdResponse.getWeight())
                            .putInt(PREFS_BASE_EXP, restIdResponse.getBase_experience())
                            .putString(PREFS_SPRITE, restIdResponse.getSprites().getFront_default())
                            .apply();
  */
                }

                @Override
                public void onFailure(Call<RestIdResponse> call, Throwable t) {
                    Log.d("ERREUR", "API K.O.");
                }
            });
       // }
    }
}
