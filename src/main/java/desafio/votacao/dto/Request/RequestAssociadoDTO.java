package desafio.votacao.dto.Request;

import desafio.votacao.model.StatusVoto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class RequestAssociadoDTO {

    private Long id;

    @CPF
    @NotBlank
    private String cpf;

    private StatusVoto status;


    @NotBlank
    private String nome;

    private List<Long> pautasId = new ArrayList<>();

}
