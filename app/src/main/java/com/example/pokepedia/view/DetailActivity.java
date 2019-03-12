package com.example.pokepedia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokepedia.R;
import com.example.pokepedia.model.RestIdResponse;
import com.example.pokepedia.model.Sprite;
import com.example.pokepedia.restapi.PokemonRestAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    static final String BASE_URL = "https://pokeapi.co/api/v2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        downloadPokemon();
    }

    public void downloadPokemon() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokemonRestAPI pokemonAPI = retrofit.create(PokemonRestAPI.class);
        int id = getIntent().getIntExtra("key_id", 1);

        Call<RestIdResponse> call = pokemonAPI.getDetailPokemon(id);

        call.enqueue(new Callback<RestIdResponse>() {
            @Override
            public void onResponse(Call<RestIdResponse> call, Response<RestIdResponse> response) {
                RestIdResponse restIdResponse = response.body();
                showDetails(restIdResponse.getName(),
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

    public void showDetails (String name, int id, int height, int weight, int base_exp, Sprite sprite) {
        TextView nameView = findViewById(R.id.name);
        nameView.setText(name);

        TextView idView = findViewById(R.id.id);
        String idString = String.valueOf(id);
        idView.setText("#"+idString);

        TextView heightView = findViewById(R.id.height);
        heightView.setText("Height : "+height+" m");

        TextView weightView = findViewById(R.id.weight);
        weightView.setText("Weight : "+weight+" kg");

        TextView base_expView = findViewById(R.id.base_exp);
        base_expView.setText("Base experience : "+base_exp+" xp");

        ImageView spriteView = findViewById(R.id.icon_detail);
        Picasso.with(this).load(sprite.getFront_default()).into(spriteView);
    }
}
