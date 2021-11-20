package com.mobileapps.reto2_appmov.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobileapps.reto2_appmov.R;
import com.mobileapps.reto2_appmov.activities.DetailsActivity;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> {

    // Una lista de pokemones
    private ArrayList<Pokemon> pokemons;
    private Trainer trainer;
    private boolean isDelete;


    public PokemonAdapter(){
        pokemons = new ArrayList<>();
        trainer = new Trainer();
        isDelete = false;
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

    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        notifyItemInserted(pokemons.size()-1);
    }

    public void receiveTrainer(Trainer trainer){
        this.trainer = trainer;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }


    public void onDelete(Pokemon pokemon) {
        int i = pokemons.indexOf(pokemon);
        pokemons.remove(i);
        notifyItemRemoved(i);
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }


    public void clear() {
        int size = pokemons.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                pokemons.remove(0);
            }
            notifyItemRangeRemoved(0, size);
        }
    }



}
