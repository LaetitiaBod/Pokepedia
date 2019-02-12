package com.example.pokepedia;

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
        //Lactivity.showList(listPokemon);
    }

    private List<Pokemon> downloadData() {
        List<Pokemon> list = new ArrayList<>();
        //TODO implement with the api
        //PokeApi pokeApi = new PokeApiClient();
        //for(int i=0; i<=807; i++) {
        //    Pokemon pk = pokeApi.getPokemon(i);
        //    list.add(pk);
        //}
        return list;
    }

    private void storeData (List<Pokemon> listPokemon) {
        //TODO
    }
}
