package br.com.zup.proposta.cartao.response;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.parcela.Parcela;

import java.math.BigDecimal;

public class ParcelasResponse {

    private String id;
    private int quantidade;
    private BigDecimal valor;

    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Parcela converter(Cartao cartao) {
        return new Parcela(quantidade, valor, cartao);
    }
}
