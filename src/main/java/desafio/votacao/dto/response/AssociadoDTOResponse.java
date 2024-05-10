package desafio.votacao.dto.response;

import desafio.votacao.model.StatusVoto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class AssociadoDTOResponse {

    private Long id;

    private String nome;

    private String cpf;

    private StatusVoto status;


}
