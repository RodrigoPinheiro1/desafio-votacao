package desafio.votacao.service;

import desafio.votacao.dto.PautaDTO;
import desafio.votacao.dto.request.RequestAssociadoDTO;
import desafio.votacao.dto.request.RequestSessaoVotacaoDTO;
import desafio.votacao.dto.request.RequestVotosDTO;
import desafio.votacao.dto.response.AssociadoDTO;
import desafio.votacao.dto.response.ContabilizaVotosDto;
import desafio.votacao.dto.response.ResponseSessaoVotacaoDTO;

public interface SessaoService {


    PautaDTO cadastrarPauta(PautaDTO pautaDTO);

    ResponseSessaoVotacaoDTO abrirSessao(RequestSessaoVotacaoDTO dto);

    AssociadoDTO receberVotos(RequestVotosDTO dto, Long idAssociado);

    ContabilizaVotosDto contabilizarVotos();

    AssociadoDTO cadastrarAssociado(RequestAssociadoDTO dto);


}
