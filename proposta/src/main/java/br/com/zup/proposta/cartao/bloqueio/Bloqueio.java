package br.com.zup.proposta.cartao.bloqueio;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate bloqueadoEm;

    @NotNull
    private String ip;

    @NotNull
    private String userAgent;

    @NotNull
    private Boolean status;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Cartao.class)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {

    }

    public Bloqueio(@NotNull String ip, @NotNull String userAgent, Cartao cartao) {
        this.bloqueadoEm = LocalDate.now();
        this.ip = ip;
        this.userAgent = userAgent;
        this.status = true;
        this.cartao = cartao;
    }

    public boolean getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }
}
