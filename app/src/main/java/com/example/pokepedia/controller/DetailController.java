package com.example.pokepedia.controller;

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

    static final String BASE_URL = "https://pokeapi.co/api/v2/";

    private DetailActivity activity;

    public DetailController(DetailActivity activity) {
        this.activity = activity;
    }

    public void onCreate() {

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
                }

                @Override
                public void onFailure(Call<RestIdResponse> call, Throwable t) {
                    Log.d("ERREUR", "API K.O.");
                }
            });
    }
}
