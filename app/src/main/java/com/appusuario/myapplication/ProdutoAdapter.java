package com.appusuario.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter <ProdutoAdapter.ViewHolderProduto>{

    private List<Produto> dados;

    public ProdutoAdapter(List<Produto> dados){
        this.dados = dados;
    }



    @Override
    public ProdutoAdapter.ViewHolderProduto onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_de_produto, parent, false);

        ViewHolderProduto viewHolderProduto = new ViewHolderProduto(view);

        return viewHolderProduto;
    }


    @Override
    public void onBindViewHolder(ProdutoAdapter.ViewHolderProduto holder, int position) {

        if ((dados != null) && (dados.size() > 0)) {

            Produto produto = dados.get(position);

            holder.txtDescricao.setText(produto.descricao);
            holder.txtQuantidade.setText(produto.getQuantidade());
        }

    }


    @Override
    public int getItemCount() {
        return dados.size();
    }


    public class ViewHolderProduto extends RecyclerView.ViewHolder{

        public TextView txtDescricao;
        public TextView txtQuantidade;

        public ViewHolderProduto(View itemView) {
            super(itemView);

            txtDescricao  = (TextView) itemView.findViewById(R.id.textViewDescricao);
            txtQuantidade = (TextView) itemView.findViewById(R.id.textViewQuantidade);

        }
    }
}
