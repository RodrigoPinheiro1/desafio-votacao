package desafio.votacao.repository;

import desafio.votacao.model.Associado;
import desafio.votacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
}
