package com.example.usuario.cencoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.usuario.cencoapp.Activitys.DetailsMovie;
import com.example.usuario.cencoapp.Activitys.MovieRecyclerAdapter;
import com.example.usuario.cencoapp.ModelData.Movie;
import com.example.usuario.cencoapp.Presenter.MoviePresenter;
import com.example.usuario.cencoapp.Utils.Singleton;
import com.example.usuario.cencoapp.Utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Singleton app;
    Utils utils;
    RecyclerView recyclerPopular;
    MoviePresenter moviePresenter=new MoviePresenter(MainActivity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils=new Utils();
        recyclerPopular=(RecyclerView)  findViewById(R.id.recyclerPopular);

        MovieRecyclerAdapter.changeListener= new MovieRecyclerAdapter.onChangeListener() {
            @Override
            public void changeListener(Movie movie) {
                utils.irActividad(MainActivity.this,DetailsMovie.class);
            }
        };
        listarSubMotivos();
    }

    public void listarSubMotivos() {
        //utils.progressDialog(LaboratoriosActivity.this);
        moviePresenter.getPopulares(new Runnable() {
            @Override
            public void run() {
                setRecyclerView(moviePresenter.returnListPopular());
            }
        }, 0);
    }


    public void setRecyclerView(ArrayList<Movie> list) {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerPopular.setLayoutManager(mLayoutManager);
        MovieRecyclerAdapter motivosRecyclerAdapter = new MovieRecyclerAdapter(MainActivity.this, list);
        recyclerPopular.setAdapter(motivosRecyclerAdapter);
    }
}
