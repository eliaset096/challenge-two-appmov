package com.mobileapps.reto2_appmov.model;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobileapps.reto2_appmov.R;
import com.mobileapps.reto2_appmov.activities.DetailsActivity;
import com.mobileapps.reto2_appmov.activities.HomeActivity;
import com.mobileapps.reto2_appmov.util.NotExistNameException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> {

    // Una lista de pokemones
    private ArrayList<Pokemon> pokemons;

    private ArrayList<Pokemon> pokemonsSearched;

    // El trainer actual
    private Trainer trainer;

    // Constructor
    public PokemonAdapter() {
        pokemons = new ArrayList<>();
        pokemonsSearched = new ArrayList<>();
        trainer = new Trainer();
    }

    @NonNull
    @Override
    public PokemonView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pokemon_row, parent, false);
        PokemonView holder = new PokemonView(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonView holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.setPokemon(pokemon);
        holder.setTrainer(trainer);
        holder.getTvNamePokemonRow().setText(pokemon.getName());
        Glide.with(holder.getIvPhotoPokemonRow().getContext()).load(pokemon.getAvatarUri()).into(holder.getIvPhotoPokemonRow());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    /**
     * Agrega un pokemon a la lista
     */
    public void addPokemon(Pokemon pokemon) {
        pokemons.add(0, pokemon);
        notifyItemInserted(0);
    }

    /**
     * Recibe el trainer actual
     * @param trainer
     */
    public void receiveTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    /**
     * Limpia la lista de pokemones
     */
    public void clear() {
        int size = pokemons.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                pokemons.remove(0);
            }
            notifyItemRangeRemoved(0, size);
        }
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public ArrayList<Pokemon> getPokemonsSearched() {
        return pokemonsSearched;
    }

    public void setPokemonsSearched(ArrayList<Pokemon> pokemonsSearched) {
        this.pokemonsSearched = pokemonsSearched;
    }
}
