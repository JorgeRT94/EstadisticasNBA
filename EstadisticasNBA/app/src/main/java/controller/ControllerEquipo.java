package controller;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.estadisticasnba.R;

import logic.AdaptadorEquipo;
import logic.LogicEquipo;

public class ControllerEquipo {

    public static final String DOMINIO = "http://35.180.196.195/";
    public static int conferenciaElegida;

    public static void getEquipos(int conferencia) {

        conferenciaElegida = conferencia;
        Log.i("JORGE", "llega al controlador");
        new LogicEquipo.loadEquiposAsync().execute(DOMINIO + "get-equipos-android.php?conferencia=" + conferencia);
    }

    public static void imagenFondoConferencia(ImageView img, Context context) {

        if (conferenciaElegida == 1) {
            Glide
                    .with(context)
                    .load("android.resource://com.example.estadisticasnba/" + R.drawable.eastern)
                    .into(img);
        } else {

            Glide
                    .with(context)
                    .load("android.resource://com.example.estadisticasnba/" + R.drawable.western)
                    .into(img);
        }
    }
}
