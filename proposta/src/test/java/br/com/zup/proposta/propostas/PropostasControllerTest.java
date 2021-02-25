package br.com.zup.proposta.propostas;

import br.com.zup.proposta.builder.NovaPropostaBuilder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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

import java.math.BigDecimal;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PropostasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PropostasRepository repository;

    @Test
    public void criaUmaNovaPropostaElegivel() throws Exception {
        PropostasRequest request = new NovaPropostaBuilder()
                                        .comDocumento("42301179060")
                                        .comNome("teste")
                                        .comEmail("teste@email.com")
                                        .comEndereco("Rua B, 22")
                                        .comSalario(new BigDecimal(5000))
                                        .constroi();

        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        Proposta proposta = request.toModel();

        Mockito.when(repository.save(proposta)).thenReturn(proposta);

        mockMvc.perform(MockMvcRequestBuilders.post("/propostas")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
