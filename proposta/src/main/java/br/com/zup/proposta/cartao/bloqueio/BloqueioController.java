package br.com.zup.proposta.cartao.bloqueio;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoClient;
import br.com.zup.proposta.cartao.CartaoRepository;
import br.com.zup.proposta.cartao.StatusCartao;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

    private final CartaoRepository cartaoRepository;
    private final CartaoClient client;

    public BloqueioController (CartaoRepository cartaoRepository, CartaoClient client) {
        this.client = client;
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> bloquear(@PathVariable @NotNull Long id, HttpServletRequest infos, UriComponentsBuilder uriComponentsBuilder) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if(possivelCartao.isPresent()) {
            Set<Bloqueio> listaBloqueios = possivelCartao.get().getBloqueios();
            for (Bloqueio bloqueio: listaBloqueios) {
                if(bloqueio.getStatus()) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("O cartão informado já se encontra como bloqueado no sistema");
                }
            }
            Bloqueio bloqueio = new Bloqueio(infos.getRemoteAddr(), infos.getHeader("User-Agent"), possivelCartao.get());
            possivelCartao.get().incluiBloqueio(bloqueio);
            cartaoRepository.save(possivelCartao.get());


            try {
                String resposta = client.bloqueiaCartao(possivelCartao.get().getNumeroCartao(), new BloqueioRequest("Sistema"));
                System.out.println(resposta);
                possivelCartao.get().setStatus(StatusCartao.BLOQUEADO);
            }catch (FeignException.UnprocessableEntity e) {
                return ResponseEntity.badRequest().build();
            }

            URI location = uriComponentsBuilder.path("/bloqueio/{id}").buildAndExpand(bloqueio.getId()).toUri();
            return ResponseEntity.ok(location);

        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
