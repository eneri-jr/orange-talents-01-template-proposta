package br.com.zup.proposta.cartao.response;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.vencimento.Vencimento;

import java.time.LocalDateTime;

public class VencimentoResponse {

    private String id;
    private int dia;
    private LocalDateTime dataDeCriacao;

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Vencimento converter(Cartao cartao) {
        return new Vencimento(dia, dataDeCriacao, cartao);
    }
}
