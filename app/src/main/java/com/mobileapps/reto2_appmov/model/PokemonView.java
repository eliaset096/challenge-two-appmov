package com.mobileapps.reto2_appmov.model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapps.reto2_appmov.R;
import com.mobileapps.reto2_appmov.activities.DetailsActivity;

public class PokemonView extends RecyclerView.ViewHolder {

    // UI
    private TextView tvNamePokemonRow;
    private ImageView ivPhotoPokemonRow;
    // State
    private Pokemon pokemon;
    private Trainer trainer;


    /*ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), this::onResult
    );*/



    public PokemonView(@NonNull View itemView) {
        super(itemView);
        Context context = itemView.getContext();

        tvNamePokemonRow = itemView.findViewById(R.id.tvNamePokemonRow);
        ivPhotoPokemonRow = itemView.findViewById(R.id.ivPhotoPokemonRow);

        itemView.setOnLongClickListener(
                v -> {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("pokemon", pokemon);
                    intent.putExtra("trainer", trainer);
                    context.startActivity(intent);
                    return true;
                }
        );


    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
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
