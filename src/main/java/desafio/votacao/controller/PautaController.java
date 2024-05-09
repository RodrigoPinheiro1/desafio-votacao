package desafio.votacao.controller;

import desafio.votacao.dto.PautaDTO;
import desafio.votacao.service.PautaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pautas")
@RequiredArgsConstructor
public class PautaController {


    private final PautaService pautaService;


    @PostMapping()
    public ResponseEntity<PautaDTO> cadastraPauta(@Valid @RequestBody PautaDTO PautaDTO) {


        PautaDTO pautaDTO = pautaService.cadastrarPauta(PautaDTO);

        return new ResponseEntity<>(pautaDTO, HttpStatus.CREATED);

    }


}
