package med.voll.api.controller.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {


    Page<Medico> findAllByActivoTrue(Pageable paginacion);

//Primer commit intellij
    @Query("""
            SELECT m FROM Medico m WHERE m.especialidad = :especialidad AND 
            m.activo = 1 and
            m.id not in (select c.medico_id from Cita c where
            c.fecha = :fecha)    
            order by random() limit 1
           """)
    Medico selecionarMedicoConEspecialidadEnFecha(Especialidad especialidad,
                                                  LocalDateTime fecha);

}
