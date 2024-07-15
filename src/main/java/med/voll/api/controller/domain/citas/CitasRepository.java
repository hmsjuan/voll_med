package med.voll.api.controller.domain.citas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CitasRepository extends JpaRepository<Citas, Long> {



    Boolean existsByIdAndFechaBetween(Long idPaciente,
                                      LocalDateTime primerHorario,
                                      LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndFecha(Long aLong, LocalDateTime fecha);


}
