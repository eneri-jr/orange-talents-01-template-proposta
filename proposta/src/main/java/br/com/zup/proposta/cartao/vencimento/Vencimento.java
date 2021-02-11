package br.com.zup.proposta.cartao.vencimento;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Vencimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int dia;

    @NotNull
    private LocalDateTime dataDeCriacao;

    @OneToOne(mappedBy = "vencimento")
    private Cartao cartao;

    public Vencimento(@NotNull int dia, @NotNull LocalDateTime dataDeCriacao, Cartao cartao) {
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
        this.cartao = cartao;
    }
}
