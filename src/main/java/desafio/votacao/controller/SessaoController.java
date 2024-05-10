package desafio.votacao.controller;

import desafio.votacao.dto.PautaDTO;
import desafio.votacao.dto.Request.RequestAssociadoDTO;
import desafio.votacao.dto.Request.RequestSessaoVotacaoDTO;
import desafio.votacao.dto.Request.RequestVotosDTO;
import desafio.votacao.dto.response.AssociadoDTO;
import desafio.votacao.dto.response.ContabilizaVotosDto;
import desafio.votacao.dto.response.ResponseSessaoVotacaoDTO;
import desafio.votacao.dto.response.ResponseVotosDTO;
import desafio.votacao.service.SessaoService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessoes/v1")
@RequiredArgsConstructor
public class SessaoController {


    private final SessaoService sessaoService;


    @PostMapping("/pautas")
    public ResponseEntity<PautaDTO> cadastraPauta(@Valid @RequestBody PautaDTO PautaDTO) {


        PautaDTO pautaDTO = sessaoService.cadastrarPauta(PautaDTO);

        return new ResponseEntity<>(pautaDTO, HttpStatus.CREATED);

    }

    @PostMapping
    public ResponseEntity<ResponseSessaoVotacaoDTO> abrirSessao(@Valid @RequestBody RequestSessaoVotacaoDTO dto) {


        ResponseSessaoVotacaoDTO responseSessaoVotacaoDTO = sessaoService.abrirSessao(dto);

        return new ResponseEntity<>(responseSessaoVotacaoDTO, HttpStatus.CREATED);

    }

    @PostMapping("/associados")
    public ResponseEntity<AssociadoDTO> cadastrarAssociado(@Valid @RequestBody RequestAssociadoDTO dto) {



        AssociadoDTO associadoDTO = sessaoService.cadastrarAssociado(dto);

        return new ResponseEntity<>(associadoDTO, HttpStatus.CREATED);

    }


    @PostMapping("/votos")
    public ResponseEntity<ResponseVotosDTO> receberVotos(@Valid @RequestBody RequestVotosDTO dto) {


        ResponseVotosDTO votos = sessaoService.receberVotos(dto);

        return new ResponseEntity<>(votos, HttpStatus.CREATED);

    }

    @GetMapping("/votos")
    public ResponseEntity<ContabilizaVotosDto> contabilizarVotos() {

        ContabilizaVotosDto dto = sessaoService.contabilizarVotos();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
