package desafio.votacao.repository;

import desafio.votacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {




//    @Query("select p fr")
//    Pauta contabilizarVotos(List<Pauta> pautas);

}
