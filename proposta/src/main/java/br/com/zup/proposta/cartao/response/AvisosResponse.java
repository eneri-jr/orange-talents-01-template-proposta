package br.com.zup.proposta.cartao.response;

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
}
