package med.voll.api.controller.domain.citas;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idCita")

public class DatosCancelacionCita {
    private Long idCita;
    private String motivo;

}
