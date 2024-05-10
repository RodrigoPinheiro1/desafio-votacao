package desafio.votacao.service;

import desafio.votacao.dto.PautaDTO;
import desafio.votacao.dto.Request.RequestAssociadoDTO;
import desafio.votacao.dto.Request.RequestSessaoVotacaoDTO;
import desafio.votacao.dto.Request.RequestVotosDTO;
import desafio.votacao.dto.response.AssociadoDTO;
import desafio.votacao.dto.response.ContabilizaVotosDto;
import desafio.votacao.dto.response.ResponseSessaoVotacaoDTO;
import desafio.votacao.dto.response.ResponseVotosDTO;

public interface SessaoService {


    PautaDTO cadastrarPauta(PautaDTO pautaDTO);

    ResponseSessaoVotacaoDTO abrirSessao(RequestSessaoVotacaoDTO dto);

    ResponseVotosDTO receberVotos(RequestVotosDTO dto);

    ContabilizaVotosDto contabilizarVotos();

    AssociadoDTO cadastrarAssociado(RequestAssociadoDTO dto);


}
