package com.mobileapps.reto2_appmov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobileapps.reto2_appmov.R;
import com.mobileapps.reto2_appmov.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private Button btCatchPokemon, btSearchPokemon;

    // Permite acceso a los elementos gráficos
    private ActivityHomeBinding homeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btSearchPokemon = findViewById(R.id.btSearchPokemon);
        btSearchPokemon.setOnClickListener(this::searchPokemon);

    }

    private void searchPokemon(View view) {
        Intent intent = new Intent(view.getContext(), DetailsActivity.class);
        startActivity(intent);
    }


}