package br.com.zup.proposta.cartao.parcela;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int quantidade;

    @NotNull
    private BigDecimal valor;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Cartao.class)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    public Parcela(@NotNull int quantidade, @NotNull BigDecimal valor, Cartao cartao) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.cartao = cartao;
    }
}
