package desafio.votacao.service.utils;

import desafio.votacao.exception.AssociadoInelegivelException;
import desafio.votacao.model.Associado;
import desafio.votacao.model.StatusVoto;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Elegibilidade {


        public void sortearElegibilidade(Associado associado) {

            Random random = new Random();

            StatusVoto statusVoto = random.nextBoolean() ? StatusVoto.ABLE_TO_VOTE : StatusVoto.UNABLE_TO_VOTE;

            associado.setStatus(statusVoto);

        }

    public void validarElegibilidade(Associado associado) {

        if (associado.getStatus() == StatusVoto.UNABLE_TO_VOTE) {
            throw new AssociadoInelegivelException();
        }

    }


}
