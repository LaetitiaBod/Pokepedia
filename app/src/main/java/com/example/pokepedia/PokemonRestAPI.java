package com.example.pokepedia;

import com.example.pokepedia.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonRestAPI {

    @GET("pokemon")
    Call<RestPokemonResponse> getListPokemon();
}