package desafio.votacao.dto.Request;

import desafio.votacao.model.Pauta;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.Date;
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

    @NotBlank
    private String nome;

    private List<Long> pautasId = new ArrayList<>();

}
