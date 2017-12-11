package com.appusuario.myapplication;

import android.os.StrictMode;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.IOException;




public class WebService {

/*
    private int TIMEOUT_MILISEC = 3000;
    private static WebService instance;
    private DefaultHttpClient httpClient;

    public static DefaultHttpClient getWebServiceInstance(){
        if (instance == null){
            instance = new WebService();

        }
        return instance.httpClient;
    }
    */

    public String retornoGet(String url) {

        HttpClient httpClient = new DefaultHttpClient();

        HttpGet retornoget = new HttpGet(url);

        String retorno = "";



        try {


            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            ResponseHandler<String> responseHandler = new BasicResponseHandler();

            String responseBody = httpClient.execute(retornoget, responseHandler);

            retorno = responseBody;

        } catch (IOException e) {

            e.printStackTrace();

        }

        return retorno;

    }

}