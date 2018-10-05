package com.example.usuario.cencoapp.Utils;

import android.content.Context;

import com.example.usuario.cencoapp.R;
import com.example.usuario.cencoapp.Services.CencoApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientApi {


    private static CencoApi instance;

    public static CencoApi getClient(Context context) {
        if(instance == null) {
            OkHttpClient localClient = ClienteRetrofit.getSSLConfig(context);

            assert localClient != null;
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(context.getResources().getString(R.string.url_server))
                    .client(localClient)
                    .addConverterFactory(GsonConverterFactory.create());
            instance = builder.build()
                    .create(CencoApi.class);
        }
        return instance;
    }

}
