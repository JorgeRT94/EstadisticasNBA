package com.example.estadisticasnba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import controller.ControllerEquipo;

public class ConferenciaActivity extends AppCompatActivity {

    public static Context context;
    ImageButton btnEste;
    ImageButton btnOeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conferencia_activity);
        this.setTitle(R.string.titulo_conferencias);

        context = this;

        btnEste = findViewById(R.id.btnConferenciaEste);
        btnEste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ControllerEquipo.getEquipos(1);
            }
        });

        btnOeste = findViewById(R.id.btnConferenciaOeste);
        btnOeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ControllerEquipo.getEquipos(2);
            }
        });

    }
}