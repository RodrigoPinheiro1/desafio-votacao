package desafio.votacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static desafio.votacao.ConstantTest.*;


@SpringBootTest
@AutoConfigureMockMvc
class SessaoControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final String uri = "/sessoes/v1";

    @Test
    void cadastraPauta() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(uri + "/pautas").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PAUTA_DTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void abrirSessao() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(uri).
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(REQUEST_SESSAO_VOTACAO_DTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void cadastrarAssociado() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.post(uri + "/associados").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(REQUEST_ASSOCIADO_DTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());


    }

//    @Test
//    void receberVotos() throws Exception { METODO aleatorio de permissao, pode nao funcionar por causa da regra de negocio
//
//
//        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/votos/1").
//                        contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(REQUEST_VOTOS_DTO)))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//    }

    @Test
    void contabilizarVotos() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/votos"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}