package com.mobileapps.reto2_appmov.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapps.reto2_appmov.R;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> implements PokemonView.OnSelectPokemon {

    //
    private ArrayList<Pokemon> pokemons;


    @NonNull
    @Override
    public PokemonView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pokemon_row, parent, false);
        PokemonView skeleton = new PokemonView(view);
        skeleton.setListener(this);
        return skeleton;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonView holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.setPokemon(pokemon);
        holder.getTvNamePokemonRow().setText(pokemon.getName());

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        notifyItemInserted(pokemons.size() - 1);
    }


    @Override
    public void onSelect(Pokemon pokemon) {
        // La acción cuando se selecciona un pokemon en la lista


    }
}
