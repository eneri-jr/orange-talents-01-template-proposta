package br.com.zup.proposta.cartao;

import br.com.zup.proposta.cartao.bloqueio.BloqueioRequest;
import br.com.zup.proposta.cartao.carteira.CarteiraRequest;
import br.com.zup.proposta.cartao.response.CartaoResponse;
import br.com.zup.proposta.cartao.response.CarteirasResponse;
import br.com.zup.proposta.cartao.viagem.ViagemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url="${cartoes.host}")
public interface CartaoClient {

    @GetMapping
    CartaoResponse consultaCartao(@RequestParam("idProposta") String id);

    @PostMapping(value = "/{id}/bloqueios")
    String bloqueiaCartao(@PathVariable  String id, BloqueioRequest request);

    @PostMapping(value = "/{id}/avisos")
    String cadastraViagem(@PathVariable String id, ViagemRequest request);

    @PostMapping(value = "/{id}/carteiras")
    CarteirasResponse cadastraCarteira(@PathVariable String id, CarteiraRequest request);
}
