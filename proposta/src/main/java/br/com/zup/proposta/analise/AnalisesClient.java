package br.com.zup.proposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analises", url="${analise.host}")
public interface AnalisesClient {

    @PostMapping
    AnaliseResponse analises(@RequestBody AnaliseRequest analiseRequest);

}
