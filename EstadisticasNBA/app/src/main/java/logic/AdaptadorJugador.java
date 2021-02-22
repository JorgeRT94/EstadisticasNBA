package logic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.estadisticasnba.DetallesJugadores;
import com.example.estadisticasnba.R;

import java.util.ArrayList;

import controller.ControllerEquipo;
import controller.ControllerJugador;
import model.Jugador;

public class AdaptadorJugador extends RecyclerView.Adapter<AdaptadorJugador.HolderJugador> {

    Context context;
    public static int posicion;
    public static int idJugador;

    public AdaptadorJugador(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HolderJugador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jugador, parent, false);
        return new HolderJugador(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderJugador holder, final int position) {

        holder.txtNombreJugador.setText(LogicJugador.listJugadores.get(position).getNombreJugador());
        holder.txtEdadJugador.setText("" + LogicJugador.listJugadores.get(position).getEdad());
        holder.txtAlturaJugador.setText("" + LogicJugador.listJugadores.get(position).getAltura());
        holder.txtAlturaJugador.setText("" + LogicJugador.listJugadores.get(position).getAltura());
        Uri img = Uri.parse(ControllerJugador.DOMINIO + "jugadores/" + LogicJugador.listJugadores.get(position).getIdJugador() + ".png");

        Glide
                .with(context)
                .load(img)
                .placeholder(R.drawable.nba)
                .into(holder.imgJugador);

        holder.cvJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicion = position;
                idJugador = LogicJugador.listJugadores.get(position).getIdJugador();
                Intent intentDetallesJugador = new Intent (context, DetallesJugadores.class);
                context.startActivity(intentDetallesJugador);
            }
        });

    }

    @Override
    public int getItemCount() {
        return LogicJugador.listJugadores.size();
    }

    public void refrescar() {
        notifyDataSetChanged();
    }

    public static class HolderJugador extends RecyclerView.ViewHolder {

        CardView cvJugador;
        TextView txtNombreJugador;
        TextView txtEdadJugador;
        TextView txtAlturaJugador;
        ImageView imgJugador;

        public HolderJugador(@NonNull View itemView) {
            super(itemView);
            txtNombreJugador = itemView.findViewById(R.id.txtNombreJugador);
            txtEdadJugador = itemView.findViewById(R.id.txtEdadJugador);
            txtAlturaJugador = itemView.findViewById(R.id.txtAlturaJugador);
            cvJugador = itemView.findViewById(R.id.cvJugador);
            imgJugador = itemView.findViewById(R.id.imgJugador);
        }
    }
}