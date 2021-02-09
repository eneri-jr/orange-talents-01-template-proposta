package br.com.zup.proposta.propostas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostasController {

    private final PropostasRepository propostasRepository;

    public PropostasController (PropostasRepository propostasRepository) {
        this.propostasRepository = propostasRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastraProposta(@RequestBody @Valid PropostasRequest propostasRequest, UriComponentsBuilder uriComponentsBuilder) {

        Optional<Proposta> possivelProposta = propostasRepository.findByDocumento(propostasRequest.getDocumento());

        if(!possivelProposta.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Já existe uma proposta para o documento informado");
        }

        Proposta proposta = propostasRequest.toModel();
        propostasRepository.save(proposta);

        URI location = uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
