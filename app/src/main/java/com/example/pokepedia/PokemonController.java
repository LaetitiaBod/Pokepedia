package com.example.pokepedia;

import java.util.ArrayList;
import java.util.List;

public class PokemonController {

    private PokemonListActivity PLactivity;
    private DetailActivity Dactivity;

    public PokemonController(PokemonListActivity PLactivity, DetailActivity Dactivity) {
        this.PLactivity = PLactivity;
        this.Dactivity = Dactivity;
    }

    public void onCreate() {
        List<Pokemon> listPokemon = downloadData();
        storeData(listPokemon);
        PLactivity.showList(listPokemon);
    }

    private List<Pokemon> downloadData() {
        List<Pokemon> list = new ArrayList<>();
        //TODO implement with the api
        return list;
    }

    private void storeData (List<Pokemon> listPokemon) {
        //TODO
    }
}
