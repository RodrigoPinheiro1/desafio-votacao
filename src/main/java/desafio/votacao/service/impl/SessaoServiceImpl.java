package desafio.votacao.service.impl;

import desafio.votacao.dto.PautaDTO;
import desafio.votacao.dto.Request.RequestAssociadoDTO;
import desafio.votacao.dto.Request.RequestSessaoVotacaoDTO;
import desafio.votacao.dto.Request.RequestVotosDTO;
import desafio.votacao.dto.response.AssociadoDTO;
import desafio.votacao.dto.response.ContabilizaVotosDto;
import desafio.votacao.dto.response.ResponseSessaoVotacaoDTO;
import desafio.votacao.dto.response.ResponseVotosDTO;
import desafio.votacao.exception.AssociadoNotFound;
import desafio.votacao.exception.PautaNotFound;
import desafio.votacao.model.Associado;
import desafio.votacao.model.Pauta;
import desafio.votacao.model.SessaoVotacao;
import desafio.votacao.model.Voto;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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
    public ResponseVotosDTO receberVotos(RequestVotosDTO dto) {

        Voto voto = modelMapper.map(dto, Voto.class);

        Pauta pauta = pautaRepository.findById(dto.getIdPauta()).orElseThrow(PautaNotFound::new);
        Associado associado = associadoRepository.findById(dto.getIdAssociado()).orElseThrow(AssociadoNotFound::new);


        voto.setAssociado(associado);
        voto.setPauta(pauta);
        voto.setVoto(voto.getVoto());


        votoRepository.save(voto);

        return modelMapper.map(voto, ResponseVotosDTO.class);

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

        associado.setNome(associado.getNome());

        associadoRepository.save(associado);

        return modelMapper.map(associado, AssociadoDTO.class);
    }
}
