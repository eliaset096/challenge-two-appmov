package com.mobileapps.reto2_appmov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mobileapps.reto2_appmov.R;
import com.mobileapps.reto2_appmov.databinding.ActivityDetailsBinding;
import com.mobileapps.reto2_appmov.databinding.ActivityHomeBinding;

public class DetailsActivity extends AppCompatActivity {

    // Permite acceso a los elementos gráficos
    private ActivityDetailsBinding detailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}