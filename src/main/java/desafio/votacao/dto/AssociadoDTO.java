package desafio.votacao.dto;

import desafio.votacao.model.Pauta;
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
    private Voto voto;

    private Date dataVotacao = new Date();

    private List<Pauta> pautas = new ArrayList<>();

}
