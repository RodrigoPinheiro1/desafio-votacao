package desafio.votacao.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    private SessaoVotacao sessaoVotacao;


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "pautas")
    private List<Associado> associados = new ArrayList<>();


}
