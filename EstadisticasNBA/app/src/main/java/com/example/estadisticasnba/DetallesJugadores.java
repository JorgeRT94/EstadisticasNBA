package com.example.estadisticasnba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import controller.ControllerJugador;
import logic.AdaptadorJugador;
import logic.LogicJugador;

public class DetallesJugadores extends AppCompatActivity {

    public static Context context;
    public static EditText txtPpp;
    public static EditText txtApp;
    public static EditText txtRpp;
    public static Menu myMenu;
    ImageView imgJugador;
    Switch swEditable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_jugadores_activity);
        setTitle(LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getNombreJugador());

        context = this;

        imgJugador = findViewById(R.id.imgDetalleJugador);
        ControllerJugador.imgDetalleJugador(imgJugador, this);

        final SoundPool spKawhi = new SoundPool.Builder().setMaxStreams(10).build();
        final int spKawhiId = spKawhi.load(this, R.raw.kawhi, 1);

        txtPpp = findViewById(R.id.txtPpp);
        txtPpp.setText("" + LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getPpp());

        txtApp = findViewById(R.id.txtApp);
        txtApp.setText("" + LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getApp());

        txtRpp = findViewById(R.id.txtRpp);
        txtRpp.setText("" + LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getRpp());

        swEditable = findViewById(R.id.swEditable);
        swEditable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getIdJugador() == 144 ||
                            LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getIdJugador() == 124 ||
                            LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getIdJugador() == 125 ) {
                        
                        spKawhi.play(spKawhiId, 1, 1, 1, 0, 1);
                    }
                    myMenu.findItem(R.id.btnActualizarJugador).setVisible(true);
                    txtPpp.setEnabled(true);
                    txtApp.setEnabled(true);
                    txtRpp.setEnabled(true);
                } else {
                    myMenu.findItem(R.id.btnActualizarJugador).setVisible(false);
                    txtPpp.setEnabled(false);
                    txtApp.setEnabled(false);
                    txtRpp.setEnabled(false);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menudetallesjugadores, menu);

        myMenu = menu;
        myMenu.findItem(R.id.btnActualizarJugador).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnGraficoJugador:

                Intent intentGrafico = new Intent(context, GraficoJugadorActivity.class);
                context.startActivity(intentGrafico);
                break;

            case R.id.btnActualizarJugador:

                final SoundPool spRed = new SoundPool.Builder().setMaxStreams(10).build();
                final int spRedId = spRed.load(context, R.raw.red, 1);

                try {
                    AlertDialog.Builder dialogActualizar = new AlertDialog.Builder(this);
                    dialogActualizar.setTitle("Importante");
                    dialogActualizar.setMessage("¿Actualizar las estadisticas de " + LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getNombreJugador() + "?");
                    dialogActualizar.setCancelable(false);
                    dialogActualizar.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogBorrar, int id) {
                            ControllerJugador.updateJugador(txtPpp, txtApp, txtRpp);
                            spRed.play(spRedId, 1, 1, 1, 0, 1);
                            Toast.makeText(context, "Se ha actualizado las estadisticas de " + LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getNombreJugador(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialogActualizar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBorrar, int id) {
                            finish();
                        }
                    });
                    dialogActualizar.show();
                } catch (Exception e) {
                    Toast.makeText(context, "No se han podido actualizar las estadisticas", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnBorrarJugador:

                try {
                    AlertDialog.Builder dialogBorrar = new AlertDialog.Builder(this);
                    dialogBorrar.setTitle("Importante");
                    dialogBorrar.setMessage("¿Eliminar a " + LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getNombreJugador() + "?");
                    dialogBorrar.setCancelable(false);
                    dialogBorrar.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogBorrar, int id) {
                            ControllerJugador.deleteJugador();
                            Toast.makeText(context, "Se ha eliminado a " + LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getNombreJugador(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    dialogBorrar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBorrar, int id) {
                            finish();
                        }
                    });
                    dialogBorrar.show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No se ha podido eliminar el jugador", Toast.LENGTH_SHORT).show();
                }

                break;

        }
        return true;
    }
}