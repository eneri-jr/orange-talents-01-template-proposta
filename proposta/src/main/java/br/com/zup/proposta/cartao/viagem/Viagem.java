package br.com.zup.proposta.cartao.viagem;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String destino;

    @Future
    @NotNull
    private LocalDate dataTermino;

    private LocalDate criacao;

    private String ip;

    private String userAgent;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Cartao.class)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @Deprecated
    public Viagem() {

    }

    public Viagem(@NotBlank String destino, @Future @NotNull LocalDate dataTermino, String ip, String userAgent, Cartao cartao) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.criacao = LocalDate.now();
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
