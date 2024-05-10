package desafio.votacao.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import desafio.votacao.dto.PautaDTO;
import desafio.votacao.dto.Request.RequestAssociadoDTO;
import desafio.votacao.dto.Request.RequestSessaoVotacaoDTO;
import desafio.votacao.dto.Request.RequestVotosDTO;
import desafio.votacao.dto.response.AssociadoDTO;
import desafio.votacao.dto.response.ContabilizaVotosDto;
import desafio.votacao.dto.response.ResponseSessaoVotacaoDTO;
import desafio.votacao.exception.AssociadoInelegivelException;
import desafio.votacao.exception.AssociadoNotFound;
import desafio.votacao.exception.PautaNotFound;
import desafio.votacao.model.*;
import desafio.votacao.repository.AssociadoRepository;
import desafio.votacao.repository.PautaRepository;
import desafio.votacao.repository.SessaoVotacaoRepository;
import desafio.votacao.repository.VotoRepository;
import desafio.votacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class SessaoServiceImpl implements SessaoService {


    private final ModelMapper modelMapper;

    private final AssociadoRepository associadoRepository;
    private final PautaRepository pautaRepository;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final VotoRepository votoRepository;


    @Override
    public PautaDTO cadastrarPauta(PautaDTO pautaDTO) {


        Pauta pauta = modelMapper.map(pautaDTO, Pauta.class);

        pauta.setAssociados(pauta.getAssociados());
        pauta.setTitulo(pauta.getTitulo());

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

        validarElegibilidade(associado);

        associado.setId(idAssociado);
        associado.setVoto(voto);

        associadoRepository.save(associado);

        return modelMapper.map(associado, AssociadoDTO.class);

    }


    private void validarElegibilidade(Associado associado) {

        if (associado.getStatus() == StatusVoto.UNABLE_TO_VOTE) {
            throw new AssociadoInelegivelException();
        }

    }

    @Override
    public ContabilizaVotosDto contabilizarVotos() {

        ContabilizaVotosDto contabilizaVotosDto = new ContabilizaVotosDto();
        AtomicInteger votosNao = new AtomicInteger();
        AtomicInteger votosSim = new AtomicInteger();


        List<Voto> votos = votoRepository.findAll();

        votos.forEach(voto -> {


            if (voto.getVoto().equalsIgnoreCase("não")) {

                votosNao.addAndGet(1);

            } else {
                votosSim.getAndSet(votosSim.get() + 1);
            }

            contabilizaVotosDto.setVotosNao(votosNao);
            contabilizaVotosDto.setVotosSim(votosSim);

        });

        return contabilizaVotosDto;
    }

    @Override
    public AssociadoDTO cadastrarAssociado(RequestAssociadoDTO dto) {

        Associado associado = modelMapper.map(dto, Associado.class);


        dto.getPautasId().forEach(ids -> {

            Pauta pauta = pautaRepository.findById(ids).orElseThrow(PautaNotFound::new);
            associado.setPautas(Collections.singletonList(pauta));

        });

        sortearElegibilidade(associado);

        associado.setNome(associado.getNome());
        associado.setCpf(associado.getCpf());


        associadoRepository.save(associado);

        return modelMapper.map(associado, AssociadoDTO.class);
    }

    private void sortearElegibilidade(Associado associado) {


        Random random = new Random();

        StatusVoto statusVoto = random.nextBoolean() ? StatusVoto.ABLE_TO_VOTE : StatusVoto.UNABLE_TO_VOTE;

        associado.setStatus(statusVoto);


    }
}
