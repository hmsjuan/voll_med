package med.voll.api.controller.domain.citas.validaciones;

import med.voll.api.controller.domain.citas.DatosAgendarCitas;
import med.voll.api.controller.infra.errores.ValidacionDeIntegridad;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ValidadorDeCitas {

    public void validar(DatosAgendarCitas datosAgendarCitas) {
        var ahora = LocalDateTime.now();
        var horaConsulta = datosAgendarCitas.fecha();

        var diferencia30Miniutos = Duration.between(ahora, horaConsulta).toMinutes() < 30;
        if (diferencia30Miniutos) {
            throw new ValidacionDeIntegridad("La hora de la consulta no puede ser menor a 30 minutos");
        }

    }
}

