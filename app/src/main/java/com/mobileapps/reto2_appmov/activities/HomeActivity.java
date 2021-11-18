package com.mobileapps.reto2_appmov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobileapps.reto2_appmov.R;
import com.mobileapps.reto2_appmov.databinding.ActivityHomeBinding;
import com.mobileapps.reto2_appmov.model.Pokemon;
import com.mobileapps.reto2_appmov.model.Trainer;

public class HomeActivity extends AppCompatActivity {

    // El trainer actual
    private Trainer trainer;

    private FirebaseFirestore DB = FirebaseFirestore.getInstance();
    // Permite acceso a los elementos gráficos
    private ActivityHomeBinding homeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        // Se recupera el trainer actual
        trainer = (Trainer) getIntent().getExtras().get("trainer");

        // Monitorea la acción de click sobre el botón
        homeBinding.btSearchPokemon.setOnClickListener(this::searchPokemon);

        // recupera las trainer
        DB.collection("trainers").get().addOnCompleteListener(
                task -> {
                    for (DocumentSnapshot doc : task.getResult()) {
                        Trainer trainer = doc.toObject(Trainer.class);
                        //users.add(user);
                        //adapter.notifyDataSetChanged();
                    }
                }
        );


    }

    private void searchPokemon(View view) {
        Intent intent = new Intent(view.getContext(), DetailsActivity.class);
        startActivity(intent);
    }

    public void addPokemon(Pokemon pokemon) {

    }

    private void getPokemon() {

    }


}