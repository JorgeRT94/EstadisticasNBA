package logic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.estadisticasnba.AniadirJugadorActivity;
import com.example.estadisticasnba.DetallesJugadores;
import com.example.estadisticasnba.EquiposActivity;
import com.example.estadisticasnba.JugadoresActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import controller.ControllerJugador;
import model.Equipo;
import model.Jugador;

public class LogicJugador {

    public static ArrayList<Jugador> listJugadores;
    public static ArrayList<Jugador> allJugadores;
    public static int ultimoId;

    public static class loadJugadoresAsync extends AsyncTask<String, Void, Void> {

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

            Type type = new TypeToken<List<Jugador>>() {
            }.getType();

            //Si peta aqui es por los nombres de las variables, que deben ser igual que las de la consulta

            listJugadores = new Gson().fromJson(resultado, type);

            Intent intent = new Intent(EquiposActivity.context, JugadoresActivity.class);
            EquiposActivity.context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        }
    }

    public static class loadJugadoresForImg extends AsyncTask<String, Void, Void> {

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

            Type type = new TypeToken<List<Jugador>>() {
            }.getType();

            allJugadores = new Gson().fromJson(resultado, type);
            ultimoId = allJugadores.get(0).getIdJugador();

            LogicImg.uploadImg();
            new refreshJugadoresAsync().execute(ControllerJugador.DOMINIO + "get-jugadores-android.php?equipo=" + LogicEquipo.listEquipos.get(AdaptadorEquipo.posicion).getId_equipo());
        }
    }

    public static class readUpdateDeleJugadorAsync extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                bufferedReader.close();
            } catch (IOException e) {
                Toast.makeText(DetallesJugadores.context, "No se ha podido actualizar la informacion del Jugador", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new LogicJugador.refreshJugadoresAsync().execute(ControllerJugador.DOMINIO + "get-jugadores-android.php?equipo=" + LogicEquipo.listEquipos.get(AdaptadorEquipo.posicion).getId_equipo());
        }
    }

    public static class refreshJugadoresAsync extends AsyncTask<String, Void, Void> {

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

            Type type = new TypeToken<List<Jugador>>() {
            }.getType();

            listJugadores = new Gson().fromJson(resultado, type);
        }
    }


}
