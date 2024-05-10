package desafio.votacao.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestSessaoVotacaoDTO {


    private Long id;

    @NotBlank
    private String titulo;

    @NotNull
    private Long idPauta;

    private LocalDateTime tempoAbertura = LocalDateTime.now();
}
