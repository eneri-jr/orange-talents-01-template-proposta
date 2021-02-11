package br.com.zup.proposta.cartao;

import br.com.zup.proposta.cartao.response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url="${cartoes.host}")
public interface CartaoClient {

    @GetMapping
    CartaoResponse consultaCartao(@RequestParam("idProposta") String id);
}
