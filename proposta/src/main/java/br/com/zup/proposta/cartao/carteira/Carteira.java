package br.com.zup.proposta.cartao.carteira;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idSistema;

    private String email;

    private String nomeCarteira;

    private LocalDate emissao;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Cartao.class)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @Deprecated
    public Carteira() {

    }

    public Carteira(String idSistema, String email, String nomeCarteira, Cartao cartao) {
        this.idSistema = idSistema;
        this.emissao = LocalDate.now();
        this.email = email;
        this.nomeCarteira = nomeCarteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCarteira() {
        return nomeCarteira;
    }
}
