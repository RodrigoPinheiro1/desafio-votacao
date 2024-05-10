package desafio.votacao.model;

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
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private StatusVoto status;


    private String cpf;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "associado")
    private Voto voto;

    @ManyToMany
    private List<Pauta> pautas = new ArrayList<>();


}
