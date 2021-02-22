package com.example.estadisticasnba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import controller.ControllerLogin;
import logic.LogicLogin;

public class LoginActivity extends AppCompatActivity {

    public static Context context;
    public static EditText txtUsuario;
    public static EditText txtContrasenia;
    public static Button btnAcceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        context = this;

        txtUsuario = findViewById(R.id.txtNombreUsuario);
        txtContrasenia = findViewById(R.id.txtContrasenia);
        btnAcceder = findViewById(R.id.btnAcceder);

        LogicLogin.cargarPreferencias();

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ControllerLogin.comprobarAcceso();
            }
        });
    }
}