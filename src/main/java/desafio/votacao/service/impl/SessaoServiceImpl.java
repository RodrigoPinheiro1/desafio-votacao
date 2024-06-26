package desafio.votacao.service.impl;

import desafio.votacao.dto.PautaDTO;
import desafio.votacao.dto.request.RequestAssociadoDTO;
import desafio.votacao.dto.request.RequestSessaoVotacaoDTO;
import desafio.votacao.dto.request.RequestVotosDTO;
import desafio.votacao.dto.response.AssociadoDTO;
import desafio.votacao.dto.response.ContabilizaVotosDto;
import desafio.votacao.dto.response.ResponseSessaoVotacaoDTO;
import desafio.votacao.exception.AssociadoNotFound;
import desafio.votacao.exception.PautaNotFound;
import desafio.votacao.model.*;
import desafio.votacao.repository.AssociadoRepository;
import desafio.votacao.repository.PautaRepository;
import desafio.votacao.repository.SessaoVotacaoRepository;
import desafio.votacao.repository.VotoRepository;
import desafio.votacao.service.SessaoService;
import desafio.votacao.service.utils.Elegibilidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessaoServiceImpl implements SessaoService {


    private final ModelMapper modelMapper;

    private final Elegibilidade elegibilidade;
    private final AssociadoRepository associadoRepository;
    private final PautaRepository pautaRepository;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final VotoRepository votoRepository;


    @Override
    public PautaDTO cadastrarPauta(PautaDTO pautaDTO) {


        Pauta pauta = modelMapper.map(pautaDTO, Pauta.class);

        pautaRepository.save(pauta);

        return modelMapper.map(pauta, PautaDTO.class);
    }

    @Override
    public ResponseSessaoVotacaoDTO abrirSessao(RequestSessaoVotacaoDTO dto) {

        SessaoVotacao sessaoVotacao = modelMapper.map(dto, SessaoVotacao.class);

        Pauta pauta = pautaRepository.findById(dto.getIdPauta()).orElseThrow(PautaNotFound::new);

        sessaoVotacao.setTitulo(sessaoVotacao.getTitulo());
        sessaoVotacao.setPautas(Collections.singletonList(pauta));

        sessaoVotacaoRepository.save(sessaoVotacao);


        return modelMapper.map(sessaoVotacao, ResponseSessaoVotacaoDTO.class);

    }

    @Override
    public AssociadoDTO receberVotos(RequestVotosDTO dto, Long idAssociado) {

        Voto voto = modelMapper.map(dto, Voto.class);
        votoRepository.save(voto);

        Associado associado = associadoRepository.findById(idAssociado).orElseThrow(AssociadoNotFound::new);

        elegibilidade.validarElegibilidade(associado);

        associado.setId(idAssociado);
        associado.setVoto(voto);

        associadoRepository.save(associado);

        return modelMapper.map(associado, AssociadoDTO.class);

    }


    @Override
    public ContabilizaVotosDto contabilizarVotos() {
        ContabilizaVotosDto contabilizaVotosDto = new ContabilizaVotosDto();
        int votosNao = 0;
        int votosSim = 0;

        List<Voto> votos = votoRepository.findAll();

        for (Voto voto : votos) {
            if (voto.getVoto().equalsIgnoreCase("não")) {
                votosNao++;
            } else {
                votosSim++;
            }
        }

        contabilizaVotosDto.setVotosNao(votosNao);
        contabilizaVotosDto.setVotosSim(votosSim);

        return contabilizaVotosDto;
    }

    @Override
    public AssociadoDTO cadastrarAssociado(RequestAssociadoDTO dto) {

        Associado associado = modelMapper.map(dto, Associado.class);

        dto.getPautasId().forEach(ids -> {

            Pauta pauta = pautaRepository.findById(ids).orElseThrow(PautaNotFound::new);
            associado.setPautas(Collections.singletonList(pauta));

        });
        elegibilidade.sortearElegibilidade(associado);

        associadoRepository.save(associado);

        return modelMapper.map(associado, AssociadoDTO.class);
    }


}
