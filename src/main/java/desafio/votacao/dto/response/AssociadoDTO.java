package desafio.votacao.dto.response;

import desafio.votacao.dto.PautaDTO;
import desafio.votacao.model.Pauta;
import desafio.votacao.model.StatusVoto;
import desafio.votacao.model.Voto;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class AssociadoDTO {

    private Long id;

    private String nome;

    private String cpf;

    private StatusVoto status;


    private List<PautaDTOResponse> pautas = new ArrayList<>();

}
