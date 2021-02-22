package com.example.estadisticasnba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import controller.ControllerJugador;
import logic.AdaptadorJugador;
import logic.LogicEquipo;
import logic.LogicJugador;
import model.Jugador;

public class GraficoJugadorActivity extends AppCompatActivity {

    BarChart barChart;
    BarDataSet barDataSet;
    BarData barData;
    ArrayList barEntries;
    ArrayList entryJugador;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafico_jugador_activity);
        context = this;
        setTitle("Estadisticas");
        getEntries();

        barChart = findViewById(R.id.barChart);

        barDataSet = new BarDataSet(barEntries, "Resto del equipo");
        BarDataSet dataJugador = new BarDataSet(entryJugador, LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getNombreJugador());
        dataJugador.setColor(Color.RED);
        barData = new BarData();
        barData.addDataSet(barDataSet);
        barData.addDataSet(dataJugador);
        barChart.setData(barData);
        barChart.invalidate();
    }

    private void getEntries() {
        barEntries = new ArrayList<>();
        entryJugador = new ArrayList<>();
        int x = 0;
        for (Jugador j : LogicJugador.listJugadores) {
            if (j.getIdJugador() == LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getIdJugador()) {
                entryJugador.add(new BarEntry(x, j.getPpp()));
            } else {
                barEntries.add(new BarEntry(x, j.getPpp()));
            }
            x++;
        }
    }
}
