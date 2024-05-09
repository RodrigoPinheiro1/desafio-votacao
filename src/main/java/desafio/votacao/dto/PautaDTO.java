package desafio.votacao.dto;

import desafio.votacao.model.Associado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PautaDTO {


    private Long id;

    private String titulo;

    private List<AssociadoDTO> associados = new ArrayList<>();


}
