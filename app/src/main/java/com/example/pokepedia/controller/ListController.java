package com.example.pokepedia.controller;

import android.util.Log;

import com.example.pokepedia.model.Pokemon;
import com.example.pokepedia.model.RestPokemonResponse;
import com.example.pokepedia.restapi.PokemonRestAPI;
import com.example.pokepedia.view.ListActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListController {

    private ListActivity activity;
    static final String BASE_URL = "https://pokeapi.co/api/v2/";


    public ListController(ListActivity activity) {
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

        Call<RestPokemonResponse> call = pokemonAPI.getListPokemon();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                RestPokemonResponse restPokemonResponse = response.body();
                List<Pokemon> listPokemon = restPokemonResponse.getResults();
                activity.showList(listPokemon);
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                Log.d("ERREUR", "API K.O.");
            }
        });
    }
}
