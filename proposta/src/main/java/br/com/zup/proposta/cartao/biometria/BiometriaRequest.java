package br.com.zup.proposta.cartao.biometria;

import br.com.zup.proposta.cartao.Cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank
    private String digital;

    public String getDigital() {
        return digital;
    }

    public Biometria toModel(Cartao possivelCartao) {
        byte[] digital64 = Base64.getEncoder().encode(digital.getBytes());
        return new Biometria(digital64, possivelCartao);
    }

    public void setDigital(String digital) {
        this.digital = digital;
    }
}
