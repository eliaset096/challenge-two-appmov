package com.mobileapps.reto2_appmov.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapps.reto2_appmov.R;

public class PokemonView extends RecyclerView.ViewHolder {

    // UI
    private TextView tvNamePokemonRow;
    private ImageView ivPhotoPokemonRow;
    // State
    private Pokemon pokemon;
    // Listener
    private OnSelectPokemon listener;


    public PokemonView(@NonNull View itemView) {
        super(itemView);
        tvNamePokemonRow = itemView.findViewById(R.id.tvNamePokemonRow);
        ivPhotoPokemonRow = itemView.findViewById(R.id.ivPhotoPokemonRow);

    }


    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    private void selectRow(View view) {
        listener.onSelect(pokemon);
    }

    public interface OnSelectPokemon {
        void onSelect(Pokemon pokemon);
    }

    public void setListener(OnSelectPokemon listener) {
        this.listener = listener;
    }


    public TextView getTvNamePokemonRow() {
        return tvNamePokemonRow;
    }

    public void setTvNamePokemonRow(TextView tvNamePokemonRow) {
        this.tvNamePokemonRow = tvNamePokemonRow;
    }

    public ImageView getIvPhotoPokemonRow() {
        return ivPhotoPokemonRow;
    }

    public void setIvPhotoPokemonRow(ImageView ivPhotoPokemonRow) {
        this.ivPhotoPokemonRow = ivPhotoPokemonRow;
    }
}
