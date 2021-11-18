package com.mobileapps.reto2_appmov.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.mobileapps.reto2_appmov.databinding.ActivityHomeBinding;
import com.mobileapps.reto2_appmov.model.Pokemon;
import com.mobileapps.reto2_appmov.model.PokemonAdapter;
import com.mobileapps.reto2_appmov.model.Trainer;
import com.mobileapps.reto2_appmov.pokeapi.PokemonResponse;
import com.mobileapps.reto2_appmov.util.HTTPSWebUtilDomi;

import java.util.UUID;

public class HomeActivity extends AppCompatActivity {

    // El trainer actual
    private Trainer trainer;
    //
    private FirebaseFirestore DB = FirebaseFirestore.getInstance();
    // Permite acceso a los elementos gráficos
    private ActivityHomeBinding homeBinding;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        // Se recupera el trainer actual
        trainer = (Trainer) getIntent().getExtras().get("trainer");

        // recupera los pokemons del trainer
        getPokemons();

        // configuración del recycler view
        homeBinding.rvPokemonList.setHasFixedSize(true);
        homeBinding.rvPokemonList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PokemonAdapter();
        homeBinding.rvPokemonList.setAdapter(adapter);


        //
        homeBinding.btCatchPokemon.setOnClickListener(
                v -> {
                    // Atrapa un pokemon por el nombre
                    catchPokemon(homeBinding.tilCatchPokemon.getEditText().getText().toString().toLowerCase().replace(" ",""));
                    //getPokemons();
                }
        );

        // Monitorea la acción de click sobre el botón
        //homeBinding.btSearchPokemon.setOnClickListener(this::searchPokemon);

    }

    // Busca un pokemon en el api
    private void catchPokemon(String pokemonName) {
        // Falta manejar la ecepción que se lanza cuando el pokemon no se encuentra
        new Thread(
                () -> {
                    try {
                        HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                        String json = utilDomi.GETrequest(pokemonName);
                        Gson gson = new Gson();
                        PokemonResponse response = gson.fromJson(json, PokemonResponse.class);

                        addPokemon(new Pokemon(
                                UUID.randomUUID().toString(),
                                response.getSprites().getFront_default(),
                                response.getName(),
                                response.getAbilities()[0].getAbility().getName(),
                                response.getStats()[2].getBase_stat(),
                                response.getStats()[1].getBase_stat(),
                                response.getStats()[5].getBase_stat(),
                                response.getStats()[0].getBase_stat())
                        );
                    } catch (NullPointerException e) {
                        Toast.makeText(this, "El pokemon que ha buscado no existe", Toast.LENGTH_LONG).show();
                    }
                }
        ).start();
    }

    // agrega un pokemon a la coelcción
    public void addPokemon(Pokemon pokemon) {
        DB.collection("trainers").document(trainer.getId())
                .collection("pokemons").document(pokemon.getId()).set(pokemon);
    }

    // Trae los pokemons de la colección en la base de datos
    private void getPokemons() {
        DB.collection("trainers").document(trainer.getId())
                .collection("pokemons").get().addOnCompleteListener(
                task -> {
                    for (DocumentSnapshot snapshot :
                            task.getResult()) {
                        Pokemon pokemon = snapshot.toObject(Pokemon.class);
                        adapter.addPokemon(pokemon);
                    }
                }
        );
    }





}