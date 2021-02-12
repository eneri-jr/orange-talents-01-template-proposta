package br.com.zup.proposta.cartao.biometria;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/biometria")
public class BiometricaController {

    private final CartaoRepository cartaoRepository;
    private final BiometriaRepository biometriaRepository;

    public BiometricaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> incluiBiometria(@PathVariable @NotNull Long id, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriComponentsBuilder) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if(possivelCartao.isPresent()) {
                Biometria biometria = request.toModel(possivelCartao.get());
                possivelCartao.get().incluiBiometria(biometria);
                cartaoRepository.save(possivelCartao.get());
                URI location = uriComponentsBuilder.path("/biometria/{id}").buildAndExpand(biometria.getId()).toUri();
                return ResponseEntity.created(location).build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}