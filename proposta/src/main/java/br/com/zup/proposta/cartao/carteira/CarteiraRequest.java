package br.com.zup.proposta.cartao.carteira;

import br.com.zup.proposta.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String carteira;

    public CarteiraRequest(@NotBlank @Email String email, @NotBlank String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public Carteira toModel(Cartao cartao, String idSistema) {
        return new Carteira(idSistema, email, carteira, cartao);
    }
}
