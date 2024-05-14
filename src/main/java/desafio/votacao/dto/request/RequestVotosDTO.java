package desafio.votacao.dto.request;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestVotosDTO {

    private Long id;

    @Pattern(regexp = "sim|não")
    private String voto;

}
