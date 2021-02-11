package br.com.zup.proposta.cartao.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CartaoResponse {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<BloqueiosResponse> bloqueios;
    private List<AvisosResponse> avisos;
    private List<CarteirasResponse> carteiras;
    private List<ParcelasResponse> parcelas;
    private BigDecimal limite;
    private RenegociacaoResponse renegociacao;
    private VencimentoResponse vencimento;
    private String idProposta;

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<BloqueiosResponse> getBloqueios() {
        return bloqueios;
    }

    public List<AvisosResponse> getAvisos() {
        return avisos;
    }

    public List<CarteirasResponse> getCarteiras() {
        return carteiras;
    }

    public List<ParcelasResponse> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public RenegociacaoResponse getRenegociacao() {
        return renegociacao;
    }

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "CartaoResponse{" +
                "id='" + id + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                ", bloqueios=" + bloqueios +
                ", avisos=" + avisos +
                ", carteiras=" + carteiras +
                ", parcelas=" + parcelas +
                ", limite=" + limite +
                ", renegociacao=" + renegociacao +
                ", vencimento=" + vencimento +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }
}
