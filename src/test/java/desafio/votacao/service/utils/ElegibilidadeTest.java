package desafio.votacao.service.utils;

import desafio.votacao.exception.AssociadoInelegivelException;
import desafio.votacao.model.StatusVoto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static desafio.votacao.ConstantTest.ASSOCIADO;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Profile("test")
class ElegibilidadeTest {


    @InjectMocks
    private Elegibilidade elegibilidade;

    @Test
    void sortearElegibilidade() {

        elegibilidade.sortearElegibilidade(ASSOCIADO);

        // Verificar se o status de voto foi definido corretamente
        assertNotNull(ASSOCIADO.getStatus()); // Verifica se o status de voto não é nulo
        assertTrue(ASSOCIADO.getStatus() == StatusVoto.ABLE_TO_VOTE ||
                ASSOCIADO.getStatus() == StatusVoto.UNABLE_TO_VOTE);
    }


    @Test
    void validarElegibilidade() {


        ASSOCIADO.setStatus(StatusVoto.UNABLE_TO_VOTE);

        assertThrows(AssociadoInelegivelException.class, () -> {
            elegibilidade.validarElegibilidade(ASSOCIADO);
        });

    }
}