package desafio.votacao.dto.Request;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestVotosDTO {

    private Long id;

    @Pattern(regexp = "sim|não")
    private String voto;

}
