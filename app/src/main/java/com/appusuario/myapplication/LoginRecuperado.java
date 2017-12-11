package com.appusuario.myapplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erlandson on 09/12/2017.
 */

public class LoginRecuperado {

    List<Login> logins = new ArrayList<>();

    WebService ws = new WebService();
    String retornoWS = "http://192.168.0.11:8080/WSAnalyze/webresources/login/Login/Lista";
    String retorno = ws.retornoGet(retornoWS);

    public List<Login> LoginJSON(){


        Type listType = new TypeToken<ArrayList<Login>>(){}.getType();

        Gson g = new Gson();

        logins = g.fromJson(retorno, listType);

        return logins;
    }
}
