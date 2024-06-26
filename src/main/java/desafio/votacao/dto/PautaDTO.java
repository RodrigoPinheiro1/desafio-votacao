package desafio.votacao.dto;

import desafio.votacao.dto.response.AssociadoDTO;
import desafio.votacao.dto.response.ResponseVotosDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PautaDTO {


    //@JsonIgnore
    private Long id;

    @NotBlank
    private String titulo;

    private List<ResponseVotosDTO> votos = new ArrayList<>();

   // private List<AssociadoDTO> associados = new ArrayList<>();


}
