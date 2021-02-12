package br.com.zup.proposta.cartao.response;

import java.time.LocalDateTime;

public class CarteirasResponse {

    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
