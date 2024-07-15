package med.voll.api.controller.domain.citas.validaciones;

import med.voll.api.controller.domain.citas.CitasRepository;
import med.voll.api.controller.domain.citas.DatosAgendarCitas;
import med.voll.api.controller.domain.paciente.PacienteRepository;
import med.voll.api.controller.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeCitas {

    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendarCitas datosAgendarCitas) {

        var primerHorario = datosAgendarCitas
                .fecha().withHour(7);
        var ultimoHorario = datosAgendarCitas
                .fecha().withHour(18);

      var pacienteConCitas = citasRepository.existsByIdAndFechaBetween(
              datosAgendarCitas.idPaciente(), primerHorario, ultimoHorario);

      if (pacienteConCitas) {
          throw new ValidacionDeIntegridad("El paciente ya tiene una consulta");
      }

    }
}
