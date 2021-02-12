package br.com.zup.proposta.cartao.response;

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
}
