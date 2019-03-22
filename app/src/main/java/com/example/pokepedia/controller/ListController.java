package com.example.pokepedia.controller;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.pokepedia.R;
import com.example.pokepedia.model.Pokemon;
import com.example.pokepedia.model.RestPokemonResponse;
import com.example.pokepedia.restapi.PokemonRestAPI;
import com.example.pokepedia.view.ListActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class ListController {

    private ListActivity activity;
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    private static final String PREFS = "PREFS";
    private static final String PREFS_POKEMON = "DATABASE_POKEMON";
    private SharedPreferences sharedPreferences;

    public ListController(ListActivity activity) {
        this.activity = activity;
    }

    public void onCreate() {

        sharedPreferences = activity.getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);

        if(sharedPreferences.contains(PREFS_POKEMON)) {
            Gson gson = new Gson();

            String listJson = sharedPreferences.getString(PREFS_POKEMON, null);
            Type listType = new TypeToken<List<Pokemon>>(){}.getType();
            List<Pokemon> pokemonList = gson.fromJson(listJson, listType);
            activity.showList(pokemonList);

        } else {

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

                    Gson gson2 = new Gson();
                    String listJson = gson2.toJson(listPokemon);
                    sharedPreferences
                        .edit()
                        .putString(PREFS_POKEMON, listJson)
                        .apply();
                }

                @Override
                public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                    Log.d("ERREUR", "API K.O.");
                }
            });
        }
    }
}
