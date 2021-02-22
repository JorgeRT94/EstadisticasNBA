package controller;

import logic.LogicJugador;
import logic.LogicLogin;

public class ControllerLogin {

    public static void comprobarAcceso() {

        new LogicLogin.loadEquiposAsync().execute(ControllerJugador.DOMINIO + "get-usuarios-android.php");
    }
}
