package desafio.votacao.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PautaDTOResponse {


    //@JsonIgnore
    private Long id;

    @NotBlank
    private String titulo;

   // private List<ResponseVotosDTO> votos = new ArrayList<>();

   // private List<AssociadoDTO> associados = new ArrayList<>();


}
