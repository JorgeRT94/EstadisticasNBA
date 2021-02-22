package logic;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.estadisticasnba.ConferenciaActivity;
import com.example.estadisticasnba.EquiposActivity;
import com.example.estadisticasnba.JugadoresActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.Equipo;

public class LogicEquipo {

    public static ArrayList<Equipo> listEquipos = new ArrayList<Equipo>();

    public static class loadEquiposAsync extends AsyncTask<String, Void, Void> {

        String resultado;

        @Override
        protected Void doInBackground(String... strings) {

            try {

                URL url = new URL(strings[0]);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

                String buffer;
                String cadena = "";

                while ((buffer = bufferedReader.readLine()) != null) {

                    cadena = String.format("%s%s", cadena, buffer);
                }

                resultado = cadena;
                bufferedReader.close();

            } catch (IOException e) {
                resultado = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Type type = new TypeToken<List<Equipo>>() {
            }.getType();

            listEquipos = new Gson().fromJson(resultado, type);

            Intent intent = new Intent(ConferenciaActivity.context, EquiposActivity.class);
            ConferenciaActivity.context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        }
    }
}
