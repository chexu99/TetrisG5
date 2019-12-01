package com.tetris.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tetris.R;
import com.tetris.model.Player;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {




    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView user,score;
        ImageView fotoUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user=(TextView)itemView.findViewById(R.id.textUser);
            score=(TextView)itemView.findViewById(R.id.textScore);
            fotoUser=(ImageView)itemView.findViewById(R.id.imgpPlayer);
        }
    }
    public List<Player> listaPlayers;

    public RecyclerViewAdaptador(List<Player> playerList) {
        this.listaPlayers = playerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cards,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bitmap bt;
        holder.user.setText(listaPlayers.get(position).getNombre());
        holder.score.setText(listaPlayers.get(position).getScore().toString());
        bt= BitmapFactory.decodeByteArray(listaPlayers.get(position).getImg(),0, listaPlayers.get(position).getImg().length);
        holder.fotoUser.setImageBitmap(bt);
    }

    @Override
    public int getItemCount() {
        return listaPlayers.size();
    }
}
