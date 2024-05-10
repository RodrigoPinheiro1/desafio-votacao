package desafio.votacao.dto.Request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestVotosDTO {


    private Long id;

    @NotNull
    private Long idAssociado;
    @NotNull
    private Long idPauta;

    @Pattern(regexp = "sim|não")
    private String voto;

}
