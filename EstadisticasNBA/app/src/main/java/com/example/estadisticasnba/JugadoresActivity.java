package com.example.estadisticasnba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import controller.ControllerEquipo;
import controller.ControllerJugador;
import logic.AdaptadorEquipo;
import logic.AdaptadorJugador;
import logic.LogicEquipo;
import logic.LogicJugador;
import model.Equipo;
import model.Jugador;

public class JugadoresActivity extends AppCompatActivity {
    public static Context context;
    public static ImageView imgFondoEquipo;
    public static FloatingActionButton ftAddJugador;
    public static AdaptadorJugador adaptadorJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugadores_activity);
        setTitle(LogicEquipo.listEquipos.get(AdaptadorEquipo.posicion).getNombre_equipo());
        context = this;

        imgFondoEquipo = findViewById(R.id.imgEquipoFondo);
        ControllerJugador.imagenFondo(imgFondoEquipo, context);

        ftAddJugador = findViewById(R.id.ftBtnAniadirJugador);
        ftAddJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddJugador = new Intent (context, AniadirJugadorActivity.class);
                startActivity(intentAddJugador);
            }
        });

        RecyclerView rvJugadores = findViewById(R.id.rvJugadores);
        rvJugadores.setHasFixedSize(true);

        adaptadorJugador = new AdaptadorJugador(this);
        LinearLayoutManager llm = new GridLayoutManager(this, 1);
        rvJugadores.setLayoutManager(llm);
        rvJugadores.setAdapter(adaptadorJugador);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adaptadorJugador.refrescar();
    }
}