package br.com.zup.proposta.propostas;

import br.com.zup.proposta.analise.AnaliseRequest;
import br.com.zup.proposta.analise.AnaliseResponse;
import br.com.zup.proposta.analise.AnalisesClient;
import br.com.zup.proposta.builder.NovaPropostaBuilder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
@ActiveProfiles("test")
public class PropostasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PropostasRepository repository;

    @MockBean
    private AnalisesClient client;

    @Test
    public void criaUmaNovaProposta() throws Exception {
        PropostasRequest request = new NovaPropostaBuilder()
                .comDocumento("42301179060")
                .comNome("teste")
                .comEmail("teste@email.com")
                .comEndereco("Rua B, 22")
                .comSalario(new BigDecimal(5000))
                .constroi();

        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        AnaliseResponse response = new AnaliseResponse(request.getDocumento(), request.getNome(), "SEM_RESTRICAO", "1L");

        Mockito.when(client.analises(ArgumentMatchers.notNull())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/propostas")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
