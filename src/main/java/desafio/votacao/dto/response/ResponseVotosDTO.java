package desafio.votacao.dto.response;


import desafio.votacao.dto.PautaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVotosDTO {


    private Long id;

    private AssociadoDTO associado;
    private PautaDTO pauta;
    private String voto;

}
