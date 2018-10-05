package com.example.usuario.cencoapp.Activitys;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.usuario.cencoapp.R;

public class MovieRecyclerHolders extends RecyclerView.ViewHolder {
    public TextView titulo;
    public TextView desc;
    public ImageView image;
    public ProgressBar progressBar;



    View viewItem;

    public MovieRecyclerHolders(View itemView) {
        super(itemView);
        viewItem=itemView;
        titulo = (TextView)itemView.findViewById(R.id.titulo);
        desc = (TextView)itemView.findViewById(R.id.desc);
        image = (ImageView) itemView.findViewById(R.id.imageView);
        progressBar=(ProgressBar) itemView.findViewById(R.id.progressBar);
    }


}
