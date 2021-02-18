package br.com.zup.proposta.cartao.viagem;

import br.com.zup.proposta.cartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViagemRequest {

    @NotBlank
    private String destino;

    @Future
    @NotNull
    private LocalDate validoAte;

    public ViagemRequest(@NotBlank String destino, @Future @NotNull LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public Viagem toModel(Cartao cartao, String remoteAddr, String header) {
        return new Viagem(destino, validoAte, remoteAddr, header, cartao);
    }
}
