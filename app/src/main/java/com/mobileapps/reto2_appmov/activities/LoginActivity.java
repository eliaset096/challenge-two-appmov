package com.mobileapps.reto2_appmov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mobileapps.reto2_appmov.R;
import com.mobileapps.reto2_appmov.databinding.ActivityLoginBinding;
import com.mobileapps.reto2_appmov.model.Pokemon;
import com.mobileapps.reto2_appmov.model.Trainer;

import java.util.UUID;

public class LoginActivity extends AppCompatActivity {

    private FirebaseFirestore DB = FirebaseFirestore.getInstance();

    // Permite acceso a los elementos gráficos
    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        // Monitorea la acción de click sobre el botón
        loginBinding.btLogin.setOnClickListener(this::login);

    }


    /**
     * Permite loguearse en la aplicación
     *
     * @param view
     */
    private void login(View view) {
        Trainer newTrainer = new Trainer(UUID.randomUUID().toString(), loginBinding.tilTrainerName.getEditText().getText().toString());
        Query query = DB.collection("trainers").whereEqualTo("name", newTrainer.getName());
        query.get().addOnCompleteListener(
                task -> {
                    //Si el usuario no existe crearlo e iniciar sesion con él
                    if (task.getResult().size() == 0) {
                        DB.collection("trainers").document(newTrainer.getId()).set(newTrainer);
                        Intent intent = new Intent(view.getContext(), HomeActivity.class);
                        intent.putExtra("trainer", newTrainer);
                        startActivity(intent);
                    }
                    //Si ya existe, descargar el usuario e iniciar sesion con el
                    else {
                        Trainer existingTrainer = null;
                        for (DocumentSnapshot doc : task.getResult()) {
                            existingTrainer = doc.toObject(Trainer.class);
                            break;
                        }
                        if (existingTrainer.getName().equals(loginBinding.tilTrainerName.getEditText().getText().toString())) {
                            Intent intent = new Intent(this, HomeActivity.class);
                            intent.putExtra("trainer", existingTrainer);
                            startActivity(intent);
                        }
                    }
                }
        );
    }
}