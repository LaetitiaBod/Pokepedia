package com.example.pokepedia.controller;

import com.example.pokepedia.view.ListActivity;
import com.example.pokepedia.model.Pokemon;

import java.util.ArrayList;
import java.util.List;


public class PokemonController {

    private ListActivity Lactivity;

    public PokemonController(ListActivity Lactivity) {
        this.Lactivity = Lactivity;
    }

    public void onCreate() {
        List<Pokemon> listPokemon = downloadData();
        storeData(listPokemon);
        Lactivity.showList(listPokemon);
    }

    private List<Pokemon> downloadData() {
        List<Pokemon> list = new ArrayList<>();
        Lactivity.downloadList();
        return list;
    }

    private void storeData (List<Pokemon> listPokemon) {
        //TODO
    }
}
