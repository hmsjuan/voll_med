package med.voll.api.controller.domain.citas;

import com.fasterxml.jackson.annotation.JsonFormat;
import med.voll.api.controller.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosDetalleCita(
    Long id,
    Long idPaciente,
    Long idMedico,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime fecha,
    Especialidad especialidad

) {
}
