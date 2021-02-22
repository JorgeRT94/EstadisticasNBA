package com.example.estadisticasnba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import controller.ControllerEquipo;
import logic.AdaptadorEquipo;
import logic.LogicEquipo;
import model.Equipo;

public class EquiposActivity extends AppCompatActivity {

    public static Context context;
    public static ImageView imgFondoConferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(R.string.titulo_equipos);

        setContentView(R.layout.equipos_activity);

        context = this;
        imgFondoConferencia = findViewById(R.id.imgFondoConferencia);
        ControllerEquipo.imagenFondoConferencia(imgFondoConferencia, context);

        RecyclerView rvEquipos = findViewById(R.id.rvEquipos);
        rvEquipos.setHasFixedSize(true);
        AdaptadorEquipo adaptador = new AdaptadorEquipo(LogicEquipo.listEquipos, this);
        LinearLayoutManager llm = new GridLayoutManager(this, 1);
        rvEquipos.setLayoutManager(llm);
        rvEquipos.setAdapter(adaptador);
        adaptador.refrescar();

    }

}