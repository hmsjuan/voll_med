package med.voll.api.controller.domain.paciente;

import med.voll.api.controller.domain.direccion.DatosDireccion;

public record DatosRespuestaPaciente(
        long id,
        String nombre,
        String email,
        String telefono,
        String documento,

        DatosDireccion direccion
) {
    public static DatosRespuestaPaciente from(Paciente paciente) {
        return new DatosRespuestaPaciente(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                paciente.getDocumento(),
                new DatosDireccion(paciente.getDirecion().getCalle(),
                        paciente.getDirecion().getDistrito(),
                        paciente.getDirecion().getCiudad(),
                        paciente.getDirecion().getNumero(),
                        paciente.getDirecion().getComplemento())
        );
    }
}
