package com.example.pokepedia.restapi;

import com.example.pokepedia.model.RestIdResponse;
import com.example.pokepedia.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonRestAPI {

    @GET("pokemon/?limit=807")
    Call<RestPokemonResponse> getListPokemon();

    @GET ("pokemon/{id}/")
    Call<RestIdResponse> getDetailPokemon(@Path("id") int id);


}