package com.appusuario.myapplication;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DadosRecuperados {

    List<Produto> produtos = new ArrayList<>();

    WebService ws = new WebService();
    String retornoWS = "http://192.168.0.11:8080/WSAnalyze/webresources/estoque/ProdutoAbaixoDoEstoque/Lista";
    String retorno = ws.retornoGet(retornoWS);

    public DadosRecuperados(){
        produtosJSON();
    }


    public List<Produto> produtosJSON() {

        Type listType = new TypeToken<ArrayList<Produto>>(){}.getType();

        Gson g = new Gson();

        Log.v("retorno json", retorno);

        produtos = g.fromJson(retorno, listType);

        return produtos;
    }

    public List<Produto> produtosVermelhos (){

        List<Produto> produtosVermelhos = new ArrayList<>();

        for (Produto p: produtos) {
            int quantidade = Integer.parseInt(p.getQuantidade());
            if (quantidade <= 20){
                produtosVermelhos.add(p);
            }
        } return produtosVermelhos;
    }

    public List<Produto> produtosAmarelos(){

        List<Produto> produtosAmarelos = new ArrayList<>();

        for (Produto p: produtos) {
            int quantidade = Integer.parseInt(p.getQuantidade());
            if (quantidade >= 21 && quantidade <= 40){
                produtosAmarelos.add(p);
            }
        }
        return produtosAmarelos;

    }

    public List<Produto> produtosCinzas(){

        List<Produto> produtosCinzas = new ArrayList<>();

        for (Produto p: produtos) {
            int quantidade = Integer.parseInt(p.getQuantidade());
            if (quantidade >= 41){
                produtosCinzas.add(p);
            }
        }
        return produtosCinzas;
    }

}
