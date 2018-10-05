package com.example.usuario.cencoapp.Model;

import android.content.Context;

import com.example.usuario.cencoapp.ModelData.Movie;
import com.example.usuario.cencoapp.ModelData.MovieResult;
import com.example.usuario.cencoapp.R;
import com.example.usuario.cencoapp.Utils.ClientApi;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieModel {

    private Context context;
    private boolean postExitoso;
    private String responseMessage, responseError;
    MovieResult resultados;
    String token="";
    private ArrayList<Movie> moviesArraylist=new ArrayList<Movie>();

    public MovieModel(Context context){
        this.context=context;
    }


    public void obtenerSubMotivos(final Runnable onResponse,int idMotivo) {
        ClientApi.getClient(context).getPopular(context.getResources().getString(R.string.api_key)).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                resultados = response.body();
                responseMessage = response.message();
                if (response.isSuccessful()){
                    if(resultados!=null) {
                        postExitoso = true;
                        moviesArraylist=resultados.getResults();


                        if (onResponse != null) onResponse.run();
                    } else {
                        postExitoso = false;

                        if (onResponse != null) onResponse.run();
                        System.out.println("Error en la respuesta login contraseña");
                    }
                } else {
                    postExitoso = false;
                    try {
                        responseError = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (onResponse != null) onResponse.run();
                }
            }
            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                System.out.println("Error en la respuesta info user contraseña fail: " + t.getMessage() + "call: " + call.toString());
            }
        });
    }

    public ArrayList<Movie> returnListPopular(){
        return moviesArraylist;
    }

    public Object[] returnObtenerPopular() {
        return new Object[] {moviesArraylist, postExitoso, responseMessage, responseError};
    }
}
