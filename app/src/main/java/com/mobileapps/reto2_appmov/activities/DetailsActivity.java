package com.mobileapps.reto2_appmov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobileapps.reto2_appmov.databinding.ActivityDetailsBinding;
import com.mobileapps.reto2_appmov.model.Pokemon;
import com.mobileapps.reto2_appmov.model.PokemonAdapter;
import com.mobileapps.reto2_appmov.model.PokemonView;
import com.mobileapps.reto2_appmov.model.Trainer;

public class DetailsActivity extends AppCompatActivity {

    // el pokemon actual
    private Pokemon pokemon;

    // el trainer actual
    private Trainer trainer;

    // Permite acceso a los elementos gráficos
    private ActivityDetailsBinding detailsBinding;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(detailsBinding.getRoot());

        // Recupera el pokemon seleccionado en el recycler view
        pokemon = (Pokemon) getIntent().getExtras().get("pokemon");
        // Recupera el trainer al que pertenece el pokemon
        trainer = (Trainer) getIntent().getExtras().get("trainer");

        // Se actualiza los valores en el apartado gráfico con los atributos del pokemon
        Glide.with(this).load(pokemon.getAvatarUri()).into(detailsBinding.ivPhotoPokemon);
        detailsBinding.tvNamePokemon.setText(pokemon.getName());
        detailsBinding.tvPowerPokemon.setText("(" + pokemon.getAbility() + ")");
        detailsBinding.tvDefense.setText(pokemon.getDefense());
        detailsBinding.tvAttack.setText(pokemon.getAttack());
        detailsBinding.tvSpeed.setText(pokemon.getSpeed());
        detailsBinding.tvLife.setText(pokemon.getLife());

        // Elimina un pokemon de la colección y el recyclewr view
        detailsBinding.btDropPokemon.setOnClickListener(
                v -> {
                    FirebaseFirestore.getInstance().collection("trainers").document(trainer.getId())
                            .collection("pokemons").document(pokemon.getId()).delete()
                            .addOnSuccessListener(
                                    task -> {
                                        //Log.e(">>>", "Pokemon " + pokemon.getName() + " eliminado exitosamente");
                                        Toast.makeText(this,"Pokemon "+ pokemon.getName()+" eliminado exitosamente", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                            ).addOnFailureListener(
                                    e -> {
                                        Log.w(">>>", "Pokemon " + pokemon.getName() + " no ha sido eliminado - " + e);
                                    }
                    );
                }
        );
    }



}