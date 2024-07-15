package med.voll.api.controller.domain.citas.validaciones;

import med.voll.api.controller.domain.citas.DatosAgendarCitas;
import med.voll.api.controller.infra.errores.ValidacionDeIntegridad;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoCLinica implements ValidadorDeCitas {
    public void validar(DatosAgendarCitas datosAgendarCitas) {
       var domingo= DayOfWeek.SUNDAY.equals(datosAgendarCitas.fecha().getDayOfWeek());
       var antesDeApertura= datosAgendarCitas.fecha().getHour() < 7;
       var despuesDeCierre = datosAgendarCitas.fecha().getHour() > 19;

       if (domingo || antesDeApertura || despuesDeCierre) {
           throw new ValidacionDeIntegridad("El horario de atencion es de lunes a sabado de 7:00 a 19:00");
       }
    }
}
