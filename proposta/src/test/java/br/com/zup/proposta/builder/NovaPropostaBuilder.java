package br.com.zup.proposta.builder;

import br.com.zup.proposta.propostas.PropostasRequest;

import java.math.BigDecimal;

public class NovaPropostaBuilder {

    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;

    public NovaPropostaBuilder comDocumento(String documento) {
        this.documento = documento;
        return this;
    }

    public NovaPropostaBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public NovaPropostaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public NovaPropostaBuilder comEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public NovaPropostaBuilder comSalario(BigDecimal salario) {
        this.salario = salario;
        return this;
    }

    public PropostasRequest constroi() {
        return new PropostasRequest(documento, email, nome, endereco, salario);
    }
}
