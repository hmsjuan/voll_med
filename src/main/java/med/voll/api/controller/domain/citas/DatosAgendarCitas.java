package med.voll.api.controller.domain.citas;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.controller.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosAgendarCitas(
        @NotNull
        Long idPaciente,
        Long idMedico,

        @Future
        @NotNull
        LocalDateTime fecha,
        Especialidad especialidad
        )
        {

}
