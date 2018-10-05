package com.example.usuario.cencoapp.Presenter;

import android.content.Context;

import com.example.usuario.cencoapp.Model.MovieModel;
import com.example.usuario.cencoapp.ModelData.Movie;

import java.util.ArrayList;

public class MoviePresenter {

    MovieModel movieModel;
    Context context;

    public MoviePresenter(Context context){
        movieModel=new MovieModel(context);
        this.context=context;
    }

    public void getPopulares(Runnable onResponse,int id) {
        movieModel.obtenerSubMotivos(onResponse,id);
    }

    public Object[] returnSubMotivos() {
        return movieModel.returnObtenerPopular();
    }

    public ArrayList<Movie> returnListPopular(){
        return movieModel.returnListPopular();
    }


}
