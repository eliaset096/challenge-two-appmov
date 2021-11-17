package com.mobileapps.reto2_appmov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobileapps.reto2_appmov.R;
import com.mobileapps.reto2_appmov.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    // Permite acceso a los elementos gráficos
    private ActivityLoginBinding loginBinding;

    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getActionBar().hide();

        btLogin = findViewById(R.id.btLogin);

        // Monitorea la acción de click sobre el botón
        btLogin.setOnClickListener(this::login);

    }


    /**
     * Permite loguearse en la aplicación
     * @param view
     */
    private void login(View view) {
        Intent intent = new Intent(view.getContext(), HomeActivity.class);
        startActivity(intent);
        //finish();
    }




}