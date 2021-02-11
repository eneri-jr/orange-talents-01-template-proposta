package br.com.zup.proposta.cartao.renegociacao;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Renegociacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int quantidade;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDateTime dataDeCriacao;

    @OneToOne(mappedBy = "renegociacao")

    private Cartao cartao;

    public Renegociacao(@NotNull int quantidade, @NotNull BigDecimal valor, @NotNull LocalDateTime dataDeCriacao, Cartao cartao) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
        this.cartao = cartao;
    }
}
