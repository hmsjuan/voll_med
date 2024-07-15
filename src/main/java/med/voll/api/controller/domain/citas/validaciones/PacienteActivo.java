package med.voll.api.controller.domain.citas.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.controller.domain.citas.DatosAgendarCitas;
import med.voll.api.controller.domain.paciente.DatosRegistroPaciente;
import med.voll.api.controller.domain.paciente.Paciente;
import med.voll.api.controller.domain.paciente.PacienteRepository;
import med.voll.api.controller.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeCitas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendarCitas datosAgendarCitas) {
        if (datosAgendarCitas.idPaciente()   == null) {
            return;
        }

        var pacienteActivo = pacienteRepository
                .fidActivoById(datosAgendarCitas.idPaciente());
         if (!pacienteActivo) {
            throw new ValidationException("No puede agendar una cita a un paciente inactivo");
        }




    }
}
