package br.com.zup.proposta.cartao.viagem;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoClient;
import br.com.zup.proposta.cartao.CartaoRepository;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    private final CartaoRepository cartaoRepository;
    private final CartaoClient client;

    public ViagemController (CartaoRepository cartaoRepository, CartaoClient client) {
        this.client = client;
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> bloquear(@PathVariable @NotNull Long id, @RequestBody @Valid ViagemRequest request, HttpServletRequest infos) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if(possivelCartao.isPresent()) {
            try {
                client.cadastraViagem(possivelCartao.get().getNumeroCartao(), request);

                Viagem viagem = request.toModel(possivelCartao.get(), infos.getRemoteAddr(), infos.getHeader("User-Agent"));
                possivelCartao.get().incluiViagem(viagem);
                cartaoRepository.save(possivelCartao.get());

                return ResponseEntity.ok().build();
            } catch (FeignException.FeignClientException e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
