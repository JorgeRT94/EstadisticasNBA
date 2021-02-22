package controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.SoundPool;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.estadisticasnba.AniadirJugadorActivity;
import com.example.estadisticasnba.DetallesJugadores;
import com.example.estadisticasnba.R;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;

import logic.AdaptadorEquipo;
import logic.AdaptadorJugador;
import logic.LogicEquipo;
import logic.LogicJugador;

public class ControllerJugador {

    public static final String DOMINIO = "http://35.180.196.195/";

    public static void getJugadores() {
        new LogicJugador.loadJugadoresAsync().execute(DOMINIO + "get-jugadores-android.php?equipo=" + LogicEquipo.listEquipos.get(AdaptadorEquipo.posicion).getId_equipo());
    }

    public static void imagenFondo(ImageView img, Context context) {

        Glide
                .with(context)
                .load(ControllerEquipo.DOMINIO + "equipos/" + LogicEquipo.listEquipos.get(AdaptadorEquipo.posicion).getId_equipo() + ".png")
                .into(img);
    }

    public static void imgDetalleJugador(ImageView img, Context context) {

        Glide
                .with(context)
                .load(ControllerJugador.DOMINIO + "jugadores/" + LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getIdJugador() + ".png")
                .placeholder(R.drawable.nba)
                .into(img);
    }

    public static void updateJugador(EditText ppp, EditText app, EditText rpp) {

        if (Float.parseFloat(DetallesJugadores.txtPpp.getText().toString()) < 50.0
                && Float.parseFloat(DetallesJugadores.txtApp.getText().toString()) < 30.0
                && Float.parseFloat(DetallesJugadores.txtPpp.getText().toString()) < 30.0) {

            new LogicJugador.readUpdateDeleJugadorAsync().execute(DOMINIO + "update-jugador.php?ppp=" + Float.parseFloat(ppp.getText().toString()) + "&app=" + Float.parseFloat(app.getText().toString()) + "&rpp=" + Float.parseFloat(rpp.getText().toString()) + "&id_jugador=" + LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getIdJugador());
        } else {

            Toast.makeText(DetallesJugadores.context, "No se han podido actualizar las estadisticas", Toast.LENGTH_LONG).show();
        }
    }

    public static void deleteJugador() {

        new LogicJugador.readUpdateDeleJugadorAsync().execute(DOMINIO + "delete-jugador.php?id_jugador=" + AdaptadorJugador.idJugador);
    }

    public static void addJugador() {

        int edad = Integer.parseInt(AniadirJugadorActivity.txtAddEdad.getText().toString());

        if (AniadirJugadorActivity.txtAddNombre.getText().length() > 2
                && AniadirJugadorActivity.txtAddApellido.getText().length() > 3
                && Integer.parseInt(AniadirJugadorActivity.txtAddEdad.getText().toString()) > 18
                && Integer.parseInt(AniadirJugadorActivity.txtAddEdad.getText().toString()) < 45
                && Float.parseFloat(AniadirJugadorActivity.txtAddAltura.getText().toString()) > 1.60
                && Float.parseFloat(AniadirJugadorActivity.txtAddAltura.getText().toString()) < 2.60) {

            new LogicJugador.readUpdateDeleJugadorAsync().execute(DOMINIO + "insertar-jugador.php?equipo=" + LogicEquipo.listEquipos.get(AdaptadorEquipo.posicion).getId_equipo() +
                    "&nombre_jugador=" + AniadirJugadorActivity.txtAddNombre.getText() + "%20" +
                    AniadirJugadorActivity.txtAddApellido.getText() + "&edad_jugador=" +
                    AniadirJugadorActivity.txtAddEdad.getText() + "&altura_jugador=" + AniadirJugadorActivity.txtAddAltura.getText() +
                    "&ppp=" + 0.0 + "&app=" + 0.0 +
                    "&rpp=" + 0.0);
        } else {

            Toast.makeText(AniadirJugadorActivity.context, "Los datos introducidos son incorrectos", Toast.LENGTH_LONG).show();
        }
    }

    public static void uploadImg() {

        new LogicJugador.loadJugadoresForImg().execute(DOMINIO + "all-jugadores-android.php");
    }

    public static void kawhisLaught() {

        if (LogicJugador.listJugadores.get(AdaptadorJugador.posicion).getIdJugador() == 144) {

            final SoundPool spKawhi = new SoundPool.Builder().setMaxStreams(10).build();
            final int spKawhiId = spKawhi.load(DetallesJugadores.context, R.raw.kawhi, 1);

            spKawhi.play(spKawhiId, 2, 2, 1, 0, 1);
        }

    }

}
