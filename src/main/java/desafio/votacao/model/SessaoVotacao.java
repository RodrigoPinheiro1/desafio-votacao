package desafio.votacao.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class SessaoVotacao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "sessaoVotacao")
    private List<Pauta> pautas = new ArrayList<>();

    private LocalDateTime tempoAbertura = LocalDateTime.now();
}
