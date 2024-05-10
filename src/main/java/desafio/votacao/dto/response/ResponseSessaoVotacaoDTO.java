package desafio.votacao.dto.response;

import desafio.votacao.dto.PautaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseSessaoVotacaoDTO {


    private Long id;

    private String titulo;

    private List<PautaDTO> pautas = new ArrayList<>();

    private LocalDateTime tempoAbertura = LocalDateTime.now();
}
