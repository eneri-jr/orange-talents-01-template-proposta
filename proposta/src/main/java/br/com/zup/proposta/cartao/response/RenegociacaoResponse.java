package br.com.zup.proposta.cartao.response;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.renegociacao.Renegociacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoResponse {

    private String id;
    private int quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Renegociacao converter(Cartao cartao) {
        return new Renegociacao(quantidade, valor, dataDeCriacao, cartao);
    }
}
