package com.appusuario.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tLogin = findViewById(R.id.tNome);
                TextView tSenha = findViewById(R.id.tSenha);


                List<Login> listaLogin = new ArrayList<>();
                LoginRecuperado lr = new LoginRecuperado();
                listaLogin = lr.LoginJSON();

                for (Login l: listaLogin ){

                    Login login = new Login();
                    login.setUsuario(l.getUsuario());
                    login.setSenha(l.getSenha());

                    String nome = tLogin.getText().toString();
                    String senha = tSenha.getText().toString();

                    if (login.getUsuario().equals(nome)&&login.getSenha().equals(senha)){
                        alert("Login realizado com sucesso");
                        irParaDash(view);
                    }else{
                        alert("Usuário ou senha incorretos");
                    }
                }
            }

        });
    }

    private void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    private void irParaDash (View view){
        Intent intentDash = new Intent(getApplicationContext(), Dashboard.class);
        startActivity (intentDash);
    }



/*
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
    }*/
}
