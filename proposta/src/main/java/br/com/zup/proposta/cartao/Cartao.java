package br.com.zup.proposta.cartao;

import br.com.zup.proposta.cartao.aviso.Aviso;
import br.com.zup.proposta.cartao.bloqueio.Bloqueio;
import br.com.zup.proposta.cartao.carteira.Carteira;
import br.com.zup.proposta.cartao.parcela.Parcela;
import br.com.zup.proposta.cartao.renegociacao.Renegociacao;
import br.com.zup.proposta.cartao.response.CartaoResponse;
import br.com.zup.proposta.cartao.vencimento.Vencimento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;

    private LocalDateTime emissao;

    private String titular;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private Set<Bloqueio> bloqueios = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private Set<Aviso> avisos = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private Set<Carteira> carteiras = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private Set<Parcela> parcelas = new HashSet<>();

    @NotNull
    private BigDecimal limite;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "renegociacao_id", referencedColumnName = "id")
    private Renegociacao renegociacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vencimento_id", referencedColumnName = "id")
    private Vencimento vencimento;

    @NotNull
    private String idProposta;

    @Deprecated
    public Cartao() {

    }

    public Cartao(CartaoResponse cartaoResponse) {
        this.numeroCartao = cartaoResponse.getId();
        this.emissao = cartaoResponse.getEmitidoEm();
        this.titular = cartaoResponse.getTitular();
        this.limite = cartaoResponse.getLimite();
        this.vencimento = cartaoResponse.getVencimento().converter(this);
        this.idProposta = cartaoResponse.getIdProposta();
    }

}
