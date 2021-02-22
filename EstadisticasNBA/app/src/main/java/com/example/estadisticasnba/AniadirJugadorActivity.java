package com.example.estadisticasnba;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import controller.ControllerJugador;
import logic.AdaptadorEquipo;
import logic.AdaptadorJugador;
import logic.LogicEquipo;
import logic.LogicImg;
import logic.LogicJugador;

public class AniadirJugadorActivity extends AppCompatActivity {

    public static Context context;
    public static EditText txtAddNombre;
    public static EditText txtAddApellido;
    public static EditText txtAddEdad;
    public static EditText txtAddAltura;
    public static ImageView imgAddJugador;
    final SoundPool spRed = new SoundPool.Builder().setMaxStreams(10).build();
    public static boolean jugadorConImagen = false;
    Button btnAniadirJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aniadir_jugador_activity);

        context = this;

        final int spRedId = spRed.load(this, R.raw.red, 1);

        txtAddNombre = findViewById(R.id.txtAddNombre);
        txtAddApellido = findViewById(R.id.txtAddApellido);
        txtAddEdad = findViewById(R.id.txtAddEdad);
        txtAddAltura = findViewById(R.id.txtAddAltura);
        btnAniadirJugador = findViewById(R.id.btnAniadirJugador);
        btnAniadirJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    AlertDialog.Builder dialogActualizar = new AlertDialog.Builder(context);
                    dialogActualizar.setTitle("Importante");
                    dialogActualizar.setMessage("¿Agregar jugador a " + LogicEquipo.listEquipos.get(AdaptadorEquipo.posicion).getNombre_equipo() + "?");
                    dialogActualizar.setCancelable(false);
                    dialogActualizar.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogBorrar, int id) {

                            ControllerJugador.addJugador();
                            ControllerJugador.uploadImg();
                            Toast.makeText(context, txtAddNombre.getText() + " " + txtAddApellido.getText() + " se ha añadido la plantilla de " + LogicEquipo.listEquipos.get(AdaptadorEquipo.posicion).getNombre_equipo(), Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                    dialogActualizar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBorrar, int id) {
                            Toast.makeText(context, "Operacion cancelada", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    dialogActualizar.show();
                } catch (Exception e) {
                    Toast.makeText(context, "No se ha podido añadir al jugador", Toast.LENGTH_SHORT).show();
                }

            }
        });

        imgAddJugador = findViewById(R.id.imgAddJugador);
        imgAddJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFromGallery();
            }
        });
    }

    public void selectFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent, "Seleccione una aplicacion"), 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) try {
            Uri path = data.getData();
            LogicImg.bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(path), null, null);
            imgAddJugador.setImageBitmap(LogicImg.bitmap);
            jugadorConImagen = true;
        } catch (Exception e) {

        }

    }

}