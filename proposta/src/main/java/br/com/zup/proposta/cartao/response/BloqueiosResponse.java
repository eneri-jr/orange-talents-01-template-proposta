package br.com.zup.proposta.cartao.response;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.bloqueio.Bloqueio;

import java.time.LocalDateTime;

public class BloqueiosResponse {

    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    public String getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Bloqueio converter(Cartao cartao) {
        return new Bloqueio(bloqueadoEm, sistemaResponsavel, ativo, cartao);
    }
}
