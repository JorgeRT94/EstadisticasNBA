package logic;

import android.content.Context;
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
import com.example.estadisticasnba.R;

import java.util.ArrayList;

import controller.ControllerEquipo;
import controller.ControllerJugador;
import model.Equipo;

public class AdaptadorEquipo extends RecyclerView.Adapter<AdaptadorEquipo.HolderEquipo> {

    Context context;
    public static ArrayList<Equipo> equipos;
    public static int posicion;

    public AdaptadorEquipo(ArrayList<Equipo> equipos, Context context) {
        this.context = context;
        this.equipos = equipos;
    }

    @NonNull
    @Override
    public HolderEquipo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipo, parent, false);
        return new HolderEquipo(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderEquipo holder, final int position) {

        equipos = LogicEquipo.listEquipos;

        holder.txtNombreEquipo.setText(equipos.get(position).getNombre_equipo());
        holder.txtAbreviaturaEquipo.setText(equipos.get(position).getAbreviatura());
        holder.txtArenaEquipo.setText(equipos.get(position).getArena());
        Glide
                .with(context)
                .load(ControllerEquipo.DOMINIO + "equipos/" + equipos.get(position).getId_equipo() + ".png")
                .into(holder.imgEquipos);
        holder.cardEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                posicion = position;
                ControllerJugador.getJugadores();
            }
        });
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public void refrescar() {
        notifyDataSetChanged();
    }

    public static class HolderEquipo extends RecyclerView.ViewHolder {

        ImageView imgEquipos;
        CardView cardEquipo;
        TextView txtNombreEquipo;
        TextView txtAbreviaturaEquipo;
        TextView txtArenaEquipo;

        public HolderEquipo(@NonNull View itemView) {
            super(itemView);
            imgEquipos = itemView.findViewById(R.id.imgEquipo);
            cardEquipo = itemView.findViewById(R.id.cvEquipo);
            txtNombreEquipo = itemView.findViewById(R.id.txtNombreEquipo);
            txtAbreviaturaEquipo = itemView.findViewById(R.id.txtAbreviaturaEquipo);
            txtArenaEquipo = itemView.findViewById(R.id.txtArenaEquipo);
        }
    }
}