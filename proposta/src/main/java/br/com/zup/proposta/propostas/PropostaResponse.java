package br.com.zup.proposta.propostas;

public class PropostaResponse {

    private Long id;
    private String nome;
    private String status;

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.nome = proposta.getNome();
        this.status = proposta.getStatus().toString();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }
}
