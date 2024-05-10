package desafio.votacao.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pauta")
    private List<Voto> voto;


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "pautas")
    private List<Associado> associados = new ArrayList<>();


}
