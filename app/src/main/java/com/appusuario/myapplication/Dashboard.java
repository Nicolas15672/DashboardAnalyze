package com.appusuario.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    private RecyclerView recyclerViewLista;
    private RecyclerView recyclerViewVermelho;
    private RecyclerView recyclerViewAmarelo;
    private RecyclerView recyclerViewCinza;
    private ConstraintLayout constraintLayoutRecycle;
    private ConstraintLayout constraintLayoutDashBoard;
    private ProdutoAdapter produtoAdapter;
    public TextView txtDescricao;
    public TextView txtQuantidade;
    public DadosRecuperados dadosRecuperados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        SearchView searchView = (SearchView)findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchFiltro());

        //recyclerViewLista = findViewById(R.id.recycleViewLista);
        recyclerViewVermelho = findViewById(R.id.recyclerview_vermelho);
        recyclerViewAmarelo = findViewById(R.id.recyclerview_amarelo);
        recyclerViewCinza = findViewById(R.id.recyclerview_cinza);
        constraintLayoutDashBoard = findViewById(R.id.constraintLayoutDashBoard);

        LinearLayoutManager linearLayoutManagerV = new LinearLayoutManager(this);
        recyclerViewVermelho.setLayoutManager(linearLayoutManagerV);

        LinearLayoutManager linearLayoutManagerA = new LinearLayoutManager(this);
        recyclerViewAmarelo.setLayoutManager(linearLayoutManagerA);

        LinearLayoutManager linearLayoutManagerC = new LinearLayoutManager(this);
        recyclerViewCinza.setLayoutManager(linearLayoutManagerC);

        TaskProdutosCinzas tpc = new TaskProdutosCinzas();
        tpc.execute();

        TaskProdutosVermelhos tpv = new TaskProdutosVermelhos();
        tpv.execute();

        TaskProdutosAmarelos tpa = new TaskProdutosAmarelos();
        tpa.execute();

    }

    private class SearchFiltro implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String s) {

            SearchView textView = (SearchView)findViewById(R.id.search);

            List<Produto> produtoList = new ArrayList<>();

            DadosRecuperados dadosRecuperados = new DadosRecuperados();

            produtoList = dadosRecuperados.produtosJSON();

            String saida = "Produto não encontrado";

            for (Produto p: produtoList) {
                String descricao = textView.getQuery().toString();

                if (descricao.toLowerCase().equals(p.getDescricao().toLowerCase())){
                    saida = "Produto: "+p.getDescricao()+"  Quantidade: "+p.getQuantidade()+" Criticidade: "+p.getCriticidade();
                    break;
                }
            }

            alert(saida);

            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    }

    private void irParaLogin(View view) {
        Intent intentLogin = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentLogin);
    }



    private class TaskProdutosCinzas extends AsyncTask<Void, Void, ProdutoAdapter> {


        @Override
        protected ProdutoAdapter doInBackground(Void... voids) {
            DadosRecuperados dadosRecuperados = new DadosRecuperados();

            List<Produto> dados = dadosRecuperados.produtosCinzas();

            produtoAdapter = new ProdutoAdapter(dados);

            return produtoAdapter;
        }

        @Override
        protected void onPostExecute(ProdutoAdapter produtoAdapter) {
            //seta o adapter na  recyclerViewLista correta
            recyclerViewCinza.setAdapter(produtoAdapter);

            TaskProdutosCinzas ex = new TaskProdutosCinzas();
            ex.execute();
        }
    }

    private class TaskProdutosVermelhos extends AsyncTask<Void, Void, ProdutoAdapter> {


        @Override
        protected ProdutoAdapter doInBackground(Void... voids) {
            DadosRecuperados dadosRecuperados = new DadosRecuperados();

            List<Produto> dados = dadosRecuperados.produtosVermelhos();

            produtoAdapter = new ProdutoAdapter(dados);

            return produtoAdapter;
        }

        @Override
        protected void onPostExecute(ProdutoAdapter produtoAdapter) {
            recyclerViewVermelho.setAdapter(produtoAdapter);

            TaskProdutosVermelhos ex = new TaskProdutosVermelhos();
            ex.execute();
        }
    }

    private class TaskProdutosAmarelos extends AsyncTask<Void, Void, ProdutoAdapter> {


        @Override
        protected ProdutoAdapter doInBackground(Void... voids) {
            DadosRecuperados dadosRecuperados = new DadosRecuperados();

            List<Produto> dados = dadosRecuperados.produtosAmarelos();

            produtoAdapter = new ProdutoAdapter(dados);

            return produtoAdapter;
        }

        @Override
        protected void onPostExecute(ProdutoAdapter produtoAdapter) {
            recyclerViewAmarelo.setAdapter(produtoAdapter);

            TaskProdutosAmarelos ex = new TaskProdutosAmarelos();
            ex.execute();
        }
    }

    private void alert(String s){
        Toast.makeText(this, s, new Integer(90000)).show();
    }

}
