package med.voll.api.controller.domain.citas;

import med.voll.api.controller.domain.medico.Medico;
import med.voll.api.controller.domain.medico.MedicoRepository;
import med.voll.api.controller.domain.paciente.Paciente;
import med.voll.api.controller.domain.paciente.PacienteRepository;
import med.voll.api.controller.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeCitasServices {

    @Autowired
    private CitasRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;


    public void agendarCitas(DatosAgendarCitas datosAgendarCitas) {

        if (pacienteRepository.findById(datosAgendarCitas
                .idPaciente()).isPresent()) {
            throw new ValidacionDeIntegridad("El paciente no existe");

        }
        if (datosAgendarCitas.idMedico()!=null &&
                medicoRepository.existsById(datosAgendarCitas.idMedico())) {
            throw new ValidacionDeIntegridad("El medico no existe");

        }

//Validacion de integridad  validac
        var paciente = pacienteRepository.getReferenceById(datosAgendarCitas.idPaciente());

        var medico =  seleccionarMedico(datosAgendarCitas);

          var fecha = datosAgendarCitas.fecha();

        var cita = new Citas(null, paciente, medico, fecha, datosAgendarCitas.especialidad());

        consultaRepository.save(cita);

    }

    private Medico seleccionarMedico(DatosAgendarCitas datosAgendarCitas) {
        if (datosAgendarCitas.idMedico() != null) {
            return medicoRepository.getReferenceById(datosAgendarCitas.idMedico());
        }else{
            if (datosAgendarCitas.especialidad() != null) {
                throw new ValidacionDeIntegridad("La especialidad no existe");
            }
        }


        return medicoRepository.selecionarMedicoConEspecialidadEnFecha(
                datosAgendarCitas.especialidad(),
                datosAgendarCitas.fecha());

    }


}
