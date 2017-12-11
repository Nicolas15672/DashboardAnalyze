package com.appusuario.myapplication;

public class Produto {

    String descricao;
    String qtd_atual;

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQuantidade() {
        return qtd_atual;
    }

    public void setQuantidade(String quantidade) {
        this.qtd_atual = quantidade;
    }

    public String getCriticidade(){
        double quant = Double.parseDouble(qtd_atual);

        if(quant<=20){
            return "Vermelho";
        }else if(quant>=21 && quant<=40){
            return "Laranja";
        }else{
            return "Amarelo";
        }
    }
}
