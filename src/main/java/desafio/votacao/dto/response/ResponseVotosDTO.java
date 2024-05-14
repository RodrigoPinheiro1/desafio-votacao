package desafio.votacao.dto.response;


import desafio.votacao.dto.PautaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseVotosDTO {


    private Long id;

//    private AssociadoDTO associado;
    private String voto;

}
