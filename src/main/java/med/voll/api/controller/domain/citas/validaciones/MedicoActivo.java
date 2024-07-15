package med.voll.api.controller.domain.citas.validaciones;

import med.voll.api.controller.domain.citas.DatosAgendarCitas;
import med.voll.api.controller.domain.medico.MedicoRepository;
import med.voll.api.controller.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MedicoActivo implements ValidadorDeCitas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgendarCitas datosAgendarCitas) {


        if (datosAgendarCitas.idMedico()   == null) {
            return;
        }
        var medicoActivo = medicoRepository
                .fidActivoById(datosAgendarCitas.idMedico());

        if (!medicoActivo) {
            throw new ValidacionDeIntegridad("No puede agendar una cita a un medico inactivo");
        }
    }

}
