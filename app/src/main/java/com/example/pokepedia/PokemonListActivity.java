package com.example.pokepedia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import me.sargunvohra.lib.pokekotlin.*;

public class PokemonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showList(List<Pokemon> listPokemon) {

    }
}
