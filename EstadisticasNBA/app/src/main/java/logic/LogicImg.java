package logic;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.estadisticasnba.AniadirJugadorActivity;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;

import controller.ControllerJugador;

public class LogicImg {

    public static Bitmap bitmap;

    private static String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public static void uploadImg() {
        String strURL = ControllerJugador.DOMINIO + "img.php";

        if (AniadirJugadorActivity.jugadorConImagen) {

            final ProgressDialog loading = ProgressDialog.show(AniadirJugadorActivity.context, "Subiendo", "Espere por favor", false, false);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL,

                    s -> {
                        loading.dismiss();
                        Toast.makeText(AniadirJugadorActivity.context, "La imagen se ha subido correctamente", Toast.LENGTH_SHORT).show();
                    },
                    volleyError -> {
                        loading.dismiss();
                        Toast.makeText(AniadirJugadorActivity.context, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            ) {

                public Map<String, String> getParams() {
                    Hashtable<String, String> params = new Hashtable<>();
                    params.put("imgData", getStringImage(bitmap));
                    params.put("imgName", "" + LogicJugador.ultimoId);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(AniadirJugadorActivity.context);
            requestQueue.add(stringRequest);
        } else {

            Toast.makeText(AniadirJugadorActivity.context, "No se ha seleccionado imagen", Toast.LENGTH_SHORT).show();
        }

        AniadirJugadorActivity.jugadorConImagen = false;
    }
}
