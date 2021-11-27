package com.mobileapps.reto2_appmov.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;
import com.mobileapps.reto2_appmov.databinding.ActivityHomeBinding;
import com.mobileapps.reto2_appmov.model.Pokemon;
import com.mobileapps.reto2_appmov.model.PokemonAdapter;
import com.mobileapps.reto2_appmov.model.Trainer;
import com.mobileapps.reto2_appmov.pokeapi.PokemonResponse;
import com.mobileapps.reto2_appmov.util.HTTPSWebUtilDomi;
import com.mobileapps.reto2_appmov.util.NotExistNameException;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity {

    // El trainer actual
    private Trainer trainer;

    // Acceso a la base de datos de Firebase
    private FirebaseFirestore DB = FirebaseFirestore.getInstance();

    // Permite acceso a los elementos gráficos
    private ActivityHomeBinding homeBinding;

    // El adapter del recycler view
    private PokemonAdapter adapter;

    private ArrayList<Pokemon> pokemonList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        adapter = new PokemonAdapter();

        pokemonList = new ArrayList<>();

        // Se recupera el trainer logueado
        trainer = (Trainer) getIntent().getExtras().get("trainer");

        // recupera los pokemons del trainer
        getPokemons();
        //addPokemonsToList();

        // configuración del recycler view
        homeBinding.rvPokemonList.setHasFixedSize(true);
        homeBinding.rvPokemonList.setLayoutManager(new LinearLayoutManager(this));
        homeBinding.rvPokemonList.setAdapter(adapter);

        // Atrapa un nuevo pokemon al clickear el botón
        homeBinding.btCatchPokemon.setOnClickListener(
                v -> {
                    catchPokemon(homeBinding.tilCatchPokemon.getEditText().getText().toString().toLowerCase().replace(" ", ""));
                    homeBinding.tilCatchPokemon.getEditText().setText("");
                }
        );

        // Monitorea la acción de click sobre el botón
        homeBinding.btSearchPokemon.setOnClickListener(
                v -> {
                    if (homeBinding.tilSearchPokemon.getEditText().getLineCount() != 0) {
                        searchPokemon(homeBinding.tilSearchPokemon.getEditText().getText().toString().toLowerCase().replace(" ", ""));
                    }
                }
        );


        homeBinding.tilSearchPokemon.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    adapter.clear();
                    getPokemons();
                    //addPokemonsToList();
                }
                //homeBinding.tilSearchPokemon.getEditText().setClickable(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * Atrapa un nuevo pokemon por el nombre
     *
     * @param pokemonName
     */
    private void catchPokemon(String pokemonName) {
        new Thread(
                () -> {
                    try {
                        if (pokemonName != null) {
                            HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                            String json = utilDomi.GETrequest(pokemonName);
                            Gson gson = new Gson();
                            PokemonResponse response = gson.fromJson(json, PokemonResponse.class);
                            Pokemon pokemon = new Pokemon(
                                    UUID.randomUUID().toString(),
                                    response.getSprites().getFront_default(),
                                    response.getName(),
                                    response.getAbilities()[0].getAbility().getName(),
                                    response.getStats()[2].getBase_stat(),
                                    response.getStats()[1].getBase_stat(),
                                    response.getStats()[5].getBase_stat(),
                                    response.getStats()[0].getBase_stat(),
                                    putLastOrder());

                            adapter.addPokemon(pokemon);
                            addPokemon(pokemon);
                        } else {
                            throw new NullPointerException();
                        }
                    } catch (NullPointerException | FileNotFoundException e) {
                        runOnUiThread(
                                () -> {
                                    Toast.makeText(this, "No ha digitado el nombre de un pokemon\n ó El pokemon " + pokemonName + " no existe", Toast.LENGTH_LONG).show();
                                }
                        );
                    }
                }
        ).start();
    }

    /**
     *
     * @param namePokemon
     */
    public void searchPokemon(String namePokemon) {
        adapter.clear();
        DB.collection("trainers").document(trainer.getId())
                .collection("pokemons").whereEqualTo("name", namePokemon)
                .get().addOnCompleteListener(
                task -> {
                    for (DocumentSnapshot snapshot :
                            task.getResult()) {
                        Pokemon pokemon = snapshot.toObject(Pokemon.class);
                        Log.w(">>>", pokemon.getId() + " - " + pokemon.getName());
                        adapter.addPokemon(pokemon);
                        //pokemonList.add()
                        //pokemonList.add(pokemon);
                        adapter.receiveTrainer(trainer);
                    }
                }
        );
        //adapter.searchPokemon(namePokemon);
    }

    private int putLastOrder() {
        int inc = 0;
        for (int i = 0; i < adapter.getPokemons().size(); i++) {
            if (adapter.getPokemons().get(i).getCathingOrder() > inc) {
                inc = adapter.getPokemons().get(i).getCathingOrder();
            }
        }
        inc += 1;
        return inc;
    }


    /**
     * Agrega un pokemon atrapado a la lista del trainer
     *
     * @param pokemon
     */
    public void addPokemon(Pokemon pokemon) {
        DB.collection("trainers").document(trainer.getId())
                .collection("pokemons").document(pokemon.getId()).set(pokemon);
    }

    /**
     * Trae los pokemons de la colección del trainer en la base de datos
     */
    private void getPokemons() {
        DB.collection("trainers").document(trainer.getId())
                .collection("pokemons").orderBy("cathingOrder", Query.Direction.ASCENDING).get()
                .addOnCompleteListener(
                        task -> {
                            for (DocumentSnapshot snapshot :
                                    task.getResult()) {
                                Pokemon pokemon = snapshot.toObject(Pokemon.class);
                                //pokemonList.add(pokemon);
                                //addPokemonsToList();
                                //adapter.addPokemon(pokemon);
                                //pokemonList.add(pokemon);

                                adapter.addPokemon(pokemon);
                                Log.w("Order", pokemon.getCathingOrder() + "-" + pokemon.getName());
                                adapter.receiveTrainer(trainer);

                            }
                        }
                );
        //addPokemonsToList();
    }


    public void addPokemonsToList() {
        Collections.sort(pokemonList, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon pok1, Pokemon pok2) {
                return Integer.compare(pok1.getCathingOrder(), pok2.getCathingOrder());
            }
        });
        for (Pokemon pokemon :
                pokemonList) {
            adapter.addPokemon(pokemon);
            adapter.receiveTrainer(trainer);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        adapter.clear();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getPokemons();
        //addPokemonsToList();
    }


}