package med.voll.api.controller.domain.paciente;



import jakarta.validation.constraints.NotNull;
import med.voll.api.controller.domain.direccion.Direccion;

public record ActualizarPaciente(
        @NotNull
        Long id,
        String nombre,

        String telefono,

        Direccion direccion
) {


}
