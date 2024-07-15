package med.voll.api.controller.domain.citas.validaciones;

import med.voll.api.controller.domain.citas.CitasRepository;
import med.voll.api.controller.domain.citas.DatosAgendarCitas;
import med.voll.api.controller.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConCita implements ValidadorDeCitas {

    @Autowired
    private CitasRepository citasRepository;

    public void validar(DatosAgendarCitas datosAgendarCitas) {

        if (datosAgendarCitas.idMedico() == null)   return;


        var medicoConCitas = citasRepository.existsByMedicoIdAndFecha(
                datosAgendarCitas.idMedico(),
                datosAgendarCitas.fecha());

        if (medicoConCitas) {
            throw new ValidacionDeIntegridad("El medico ya tiene una consulta en este horario");
        }

    }
}
