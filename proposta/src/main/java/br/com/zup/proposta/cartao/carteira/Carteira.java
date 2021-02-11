package br.com.zup.proposta.cartao.carteira;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String email;

    @NotNull
    private LocalDateTime associadaEm;

    @NotBlank
    private String emissor;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Cartao.class)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    public Carteira(@NotBlank String email, @NotNull LocalDateTime associadaEm, @NotBlank String emissor, Cartao cartao) {
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
        this.cartao = cartao;
    }
}
