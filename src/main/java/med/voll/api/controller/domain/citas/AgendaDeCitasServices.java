package med.voll.api.controller.domain.citas;

import med.voll.api.controller.domain.citas.validaciones.ValidadorDeCitas;
import med.voll.api.controller.domain.medico.Medico;
import med.voll.api.controller.domain.medico.MedicoRepository;
import med.voll.api.controller.domain.paciente.PacienteRepository;
import med.voll.api.controller.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeCitasServices {

    @Autowired
    private CitasRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    List<ValidadorDeCitas> validadores;


    public DatosDetalleCita agendarCitas(DatosAgendarCitas datosAgendarCitas) {

        if (!pacienteRepository.findById(datosAgendarCitas
                .idPaciente()).isPresent()) {
            throw new ValidacionDeIntegridad("El paciente no existe");

        }
        if (datosAgendarCitas.idMedico()!=null &&
                !medicoRepository.existsById(datosAgendarCitas.idMedico())) {
            throw new ValidacionDeIntegridad("El medico no existe");

        }
        validadores.forEach(v -> v.validar(datosAgendarCitas));

//Validacion de integridad  validac
        var paciente = pacienteRepository.getReferenceById(datosAgendarCitas.idPaciente());

        var medico =  seleccionarMedico(datosAgendarCitas);

        if (medico== null) {
            throw new ValidacionDeIntegridad("No existe un medico disponible en este horario y " +
                    "para esta especialidad");
        }

        var fecha = datosAgendarCitas.fecha();

        var cita = new Citas(null, paciente, medico, fecha);

        consultaRepository.save(cita);

        return new DatosDetalleCita(cita);

    }

    private Medico seleccionarMedico(DatosAgendarCitas datosAgendarCitas) {
        if (datosAgendarCitas.idMedico() != null) {
            return medicoRepository.getReferenceById(datosAgendarCitas.idMedico());
        }
        if (datosAgendarCitas.especialidad() == null) {
            throw new ValidacionDeIntegridad("Debe seleccionar una especialidad o un medico");
        }



        return medicoRepository.selecionarMedicoConEspecialidadEnFecha(
                datosAgendarCitas.especialidad(),
                datosAgendarCitas.fecha());

    }


}
