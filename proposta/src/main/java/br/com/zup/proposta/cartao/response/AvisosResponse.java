package br.com.zup.proposta.cartao.response;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.aviso.Aviso;

import java.time.LocalDate;

public class AvisosResponse {

    private LocalDate validoAte;
    private String destino;

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Aviso converter(Cartao cartao) {
        return new Aviso(validoAte, destino, cartao);
    }
}
