package med.voll.api.controller.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.controller.domain.direccion.Direccion;

public record ActualizarMedico(
        @NotNull
        Long id,
        String nombre,

        String telefono,

        Direccion direccion
) {


}
