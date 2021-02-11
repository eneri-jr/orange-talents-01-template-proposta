package br.com.zup.proposta.cartao;

import br.com.zup.proposta.cartao.response.CartaoResponse;
import br.com.zup.proposta.propostas.Proposta;
import br.com.zup.proposta.propostas.PropostasRepository;
import br.com.zup.proposta.propostas.StatusProposta;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class IncluiCartao {

    private final CartaoClient client;
    private final PropostasRepository propostasRepository;
    private final CartaoRepository cartaoRepository;

    public IncluiCartao(CartaoClient client, PropostasRepository propostasRepository, CartaoRepository cartaoRepository) {

        this.client = client;
        this.propostasRepository = propostasRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void incluiCartao(){
        List<Proposta> propostasSemCartao = propostasRepository.findByCartoes(StatusProposta.ELEGIVEL);
        for(Proposta proposta: propostasSemCartao) {
            CartaoResponse cartao = client.consultaCartao(proposta.getId().toString());
            proposta.setCartao(cartao.getId());
            propostasRepository.save(proposta);

            Cartao novoCartao = new Cartao(cartao);
            cartaoRepository.save(novoCartao);
        }
    }
}
