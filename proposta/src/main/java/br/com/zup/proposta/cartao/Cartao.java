package br.com.zup.proposta.cartao;

import br.com.zup.proposta.cartao.biometria.Biometria;
import br.com.zup.proposta.cartao.bloqueio.Bloqueio;
import br.com.zup.proposta.cartao.response.CartaoResponse;
import br.com.zup.proposta.cartao.viagem.Viagem;

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

    @NotNull
    private BigDecimal limite;

    @NotNull
    private String idProposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private Set<Biometria> biometrias = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private Set<Bloqueio> bloqueios = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private StatusCartao status;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private Set<Viagem> viagens = new HashSet<>();

    @Deprecated
    public Cartao() {

    }

    public Cartao(CartaoResponse cartaoResponse) {
        this.numeroCartao = cartaoResponse.getId();
        this.emissao = cartaoResponse.getEmitidoEm();
        this.titular = cartaoResponse.getTitular();
        this.limite = cartaoResponse.getLimite();
        this.idProposta = cartaoResponse.getIdProposta();
    }

    public void incluiBiometria(Biometria biometria) {
        this.biometrias.add(biometria);
    }
    public void incluiBloqueio(Bloqueio bloqueio) {
        this.bloqueios.add(bloqueio);
    }
    public void incluiViagem(Viagem viagem) { this.viagens.add(viagem); }

    public Set<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setStatus(StatusCartao status) {
        this.status = status;
    }
}
