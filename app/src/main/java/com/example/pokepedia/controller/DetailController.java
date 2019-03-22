package com.example.pokepedia.controller;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.pokepedia.model.RestIdResponse;
import com.example.pokepedia.restapi.PokemonRestAPI;
import com.example.pokepedia.view.DetailActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Callback;

public class DetailController {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private DetailActivity activity;
    private static final String sprite_saved = "sprite_saved";
/*
    private static final String PREFS = "_PREFS";
    private static final String PREFS_NAME = "P_POKEMON";
    private static final String PREFS_ID = "P_ID";
    private static final String PREFS_HEIGH = "P_HEIGHT";
    private static final String PREFS_WEIGHT = "P_WEIGHT";
    private static final String PREFS_BASE_EXP = "P_BASE_EXP";
    private static final String PREFS_SPRITE = "P_SPRITE";
    private SharedPreferences sharedPreferences;
*/
    public DetailController(DetailActivity activity) {
        this.activity = activity;
    }

    public void onCreate() {
        /*
        sharedPreferences = activity.getBaseContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        //id que l'on récupère de listactivity //pokémon sur lequel on clique
        int id_position = activity.getIntent().getIntExtra("key_id", 1);
        //id que l'on a enregistré après l'appel api //dernier pokemon enregistré
        int id = sharedPreferences.getInt(PREFS_ID, 0);

        if(sharedPreferences.contains(PREFS_NAME) &&
            sharedPreferences.contains(PREFS_ID) &&
            sharedPreferences.contains(PREFS_HEIGH) &&
            sharedPreferences.contains(PREFS_WEIGHT) &&
            sharedPreferences.contains(PREFS_BASE_EXP) &&
            sharedPreferences.contains(PREFS_SPRITE) &&
            id_position == id) {

            String name = sharedPreferences.getString(PREFS_NAME, null);
            int height = sharedPreferences.getInt(PREFS_HEIGH, 0);
            int weight = sharedPreferences.getInt(PREFS_WEIGHT, 0);
            int base_exp = sharedPreferences.getInt(PREFS_BASE_EXP, 0);
            String front_default = sharedPreferences.getString(PREFS_SPRITE, null);
            Sprite sprite = new Sprite();
            sprite.setFront_default(front_default);

            activity.showDetails(name, id_position, height, weight, base_exp, sprite);

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
            int position = activity.getIntent().getIntExtra("key_id", 1);

            Call<RestIdResponse> call = pokemonAPI.getDetailPokemon(position);

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

                    activity.getIntent().putExtra(sprite_saved, restIdResponse.getSprites().getFront_default());
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
        //}
    }
}
