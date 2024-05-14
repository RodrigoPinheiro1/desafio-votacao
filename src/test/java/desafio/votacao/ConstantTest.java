package desafio.votacao;

import desafio.votacao.dto.PautaDTO;
import desafio.votacao.dto.request.RequestAssociadoDTO;
import desafio.votacao.dto.request.RequestSessaoVotacaoDTO;
import desafio.votacao.dto.request.RequestVotosDTO;
import desafio.votacao.dto.response.AssociadoDTO;
import desafio.votacao.dto.response.PautaDTOResponse;
import desafio.votacao.dto.response.ResponseSessaoVotacaoDTO;
import desafio.votacao.dto.response.ResponseVotosDTO;
import desafio.votacao.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ConstantTest {

    public static final PautaDTO PAUTA_DTO =
            PautaDTO.builder()
                    .id(1L)
                    .titulo("titulo")
                    .votos(Collections.singletonList(
                            ResponseVotosDTO.builder()
                                    .voto("voto")
                                    .id(1L)
                                    .build()))
                    .build();


    public static final Pauta PAUTA =
            Pauta.builder()
                    .id(1L)
                    .titulo("titulo")
                    .build();

    public static final RequestSessaoVotacaoDTO REQUEST_SESSAO_VOTACAO_DTO =

            RequestSessaoVotacaoDTO.builder()
                    .id(1L)
                    .idPauta(2L)
                    .tempoAbertura(LocalDateTime.now())
                    .titulo("TITULO")
                    .build();


    public static final RequestAssociadoDTO REQUEST_ASSOCIADO_DTO =
            RequestAssociadoDTO.builder()
                    .id(1L)
                    .cpf("12345678901")
                    .status(StatusVoto.ABLE_TO_VOTE)
                    .nome("Nome do Associado")
                    .pautasId(Arrays.asList(1L, 2L, 3L))
                    .build();


    public static final AssociadoDTO ASSOCIADO_DTO =
            AssociadoDTO.builder()
                    .id(1L)
                    .nome("João")
                    .cpf("12345678901")
                    .status(StatusVoto.ABLE_TO_VOTE)
                    .voto(ResponseVotosDTO.builder()
                            .id(1L)
                            .voto("VOTol")
                            .build())
                    .build();

    public static final ResponseSessaoVotacaoDTO RESPONSE_SESSAO_VOTACAO_DTO =

            ResponseSessaoVotacaoDTO.builder()
                    .id(1L)
                    .titulo("titulo")
                    .tempoAbertura(LocalDateTime.now())
                    .pautas(Collections.singletonList(PAUTA_DTO)).build();


    public static final SessaoVotacao SESSAO_VOTACAO =

            SessaoVotacao.builder()
                    .id(1L)
                    .titulo("titulo")
                    .tempoAbertura(LocalDateTime.now())
                    .pautas(Collections.singletonList(PAUTA)).build();


    public static final Voto VOTO =

            Voto.builder()
                    .id(1L)
                    .voto("2WADS")
                    .build();

    public static final Associado ASSOCIADO =
            Associado.builder()
                    .id(1L)
                    .nome("João")
                    .cpf("12345678901")
                    .status(StatusVoto.ABLE_TO_VOTE)
                    .voto(VOTO)
                    .build();


    public static final RequestVotosDTO REQUEST_VOTOS_DTO =
            RequestVotosDTO.builder()
                    .id(1L)
                    .voto("sim")
                    .build();


}
