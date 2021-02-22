package logic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.estadisticasnba.ConferenciaActivity;
import com.example.estadisticasnba.EquiposActivity;
import com.example.estadisticasnba.LoginActivity;
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
import model.Usuario;

public class LogicLogin {

    public static ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();

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

            Type type = new TypeToken<List<Usuario>>() {
            }.getType();

            listUsuarios = new Gson().fromJson(resultado, type);

            for (Usuario j : listUsuarios) {
                Log.i("JORGE", "Usuario: " + j);
            }

            if (usuarioCorrecto()) {
                Intent intent = new Intent(LoginActivity.context, ConferenciaActivity.class);
                LoginActivity.context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            } else {
                Toast.makeText(LoginActivity.context, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
            }


        }

        private boolean usuarioCorrecto() {
            boolean exito = false;
            Usuario usuarioLocal = new Usuario (LoginActivity.txtUsuario.getText().toString(), LoginActivity.txtContrasenia.getText().toString());

            for (Usuario u : listUsuarios) {
                if (u.getNombre().equals(usuarioLocal.getNombre()) && u.getContrasenia().equals(usuarioLocal.getContrasenia())) {
                    guardarCredenciales();
                    exito = true;

                }
            }
            return exito;
        }
    }

    public static void cargarPreferencias () {
        SharedPreferences sharedPreferences = LoginActivity.context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        LoginActivity.txtUsuario.setText(sharedPreferences.getString("usuario", ""));
        LoginActivity.txtContrasenia.setText(sharedPreferences.getString("contrasenia", ""));
    }

    public static void guardarCredenciales () {
        SharedPreferences sharedPreferences = LoginActivity.context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("usuario", LoginActivity.txtUsuario.getText().toString());
        editor.putString("contrasenia", LoginActivity.txtContrasenia.getText().toString());
        editor.commit();
    }
}
