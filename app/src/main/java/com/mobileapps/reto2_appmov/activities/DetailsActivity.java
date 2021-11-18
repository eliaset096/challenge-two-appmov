package com.mobileapps.reto2_appmov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mobileapps.reto2_appmov.R;
import com.mobileapps.reto2_appmov.databinding.ActivityDetailsBinding;
import com.mobileapps.reto2_appmov.databinding.ActivityHomeBinding;
import com.mobileapps.reto2_appmov.model.Pokemon;

public class DetailsActivity extends AppCompatActivity {


    private Pokemon pokemon;

    // Permite acceso a los elementos gráficos
    private ActivityDetailsBinding detailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(detailsBinding.getRoot());

    }


    public void removePokemon() {
    }


}