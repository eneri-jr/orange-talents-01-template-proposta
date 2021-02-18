package br.com.zup.proposta.cartao.carteira;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoClient;
import br.com.zup.proposta.cartao.CartaoRepository;
import br.com.zup.proposta.cartao.response.CarteirasResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    private final CartaoRepository cartaoRepository;
    private final CartaoClient client;

    public CarteiraController(CartaoRepository cartaoRepository, CartaoClient client) {
        this.cartaoRepository = cartaoRepository;
        this.client = client;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cadastrar(@PathVariable @NotNull Long id, @RequestBody @Valid CarteiraRequest request, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if(possivelCartao.isPresent()) {
            Set<Carteira> listaCarteiras = possivelCartao.get().getCarteiras();
            for (Carteira carteira : listaCarteiras) {
                if(carteira.getNomeCarteira().equals("PAYPAL")) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("A carteira informada já esta associada neste cartão");
                }
            }

            try {

                CarteirasResponse response = client.cadastraCarteira(possivelCartao.get().getNumeroCartao(), request);
                Carteira carteira = request.toModel(possivelCartao.get(), response.getId());
                possivelCartao.get().incluiCarteira(carteira);
                cartaoRepository.save(possivelCartao.get());
                URI location = uriComponentsBuilder.path("/carteira/{id}").buildAndExpand(carteira.getId()).toUri();
                return ResponseEntity.created(location).build();

            }catch (FeignException.UnprocessableEntity e) {

                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
