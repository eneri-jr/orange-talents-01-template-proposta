package br.com.zup.proposta.propostas;

import br.com.zup.proposta.analise.AnaliseRequest;
import br.com.zup.proposta.analise.AnaliseResponse;
import br.com.zup.proposta.analise.AnalisesClient;
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

@RestController
@RequestMapping("/propostas")
public class PropostasController {

    private final PropostasRepository propostasRepository;
    private final AnalisesClient client;

    public PropostasController (PropostasRepository propostasRepository, AnalisesClient client) {
        this.propostasRepository = propostasRepository;
        this.client = client;
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

        AnaliseRequest analise = propostasRequest.toModelAnalise(proposta);

        try {
            AnaliseResponse resposta = client.analises(analise);
            proposta.setStatus(StatusProposta.ELEGIVEL);
        }catch (FeignException.UnprocessableEntity e) {
            proposta.setStatus(StatusProposta.NAO_ELEGIVEL);
        }

        URI location = uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponse> detalharProposta(@PathVariable @NotNull Long id) {

        Optional<Proposta> proposta = propostasRepository.findById(id);

        if (!proposta.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new PropostaResponse(proposta.get()));
        }
    }
}
