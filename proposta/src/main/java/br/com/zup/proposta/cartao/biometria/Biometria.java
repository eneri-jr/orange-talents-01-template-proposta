package br.com.zup.proposta.cartao.biometria;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private byte[] digital;

    private LocalDate criacao;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Cartao.class)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @Deprecated
    public Biometria() {

    }

    public Biometria(@NotNull byte[] digital, Cartao cartao) {
        this.digital = digital;
        this.cartao = cartao;
        this.criacao = LocalDate.now();
    }

    public Long getId() {
        return id;
    }
}
