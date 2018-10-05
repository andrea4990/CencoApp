package com.example.usuario.cencoapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.usuario.cencoapp.R;

public class Utils {

    //ir actividad
    public void irActividad(Context context, Class clase) {
        Intent intent = new Intent(context, clase);
        context.startActivity(intent);
    }
}
