package med.voll.api.controller.domain.medico;

import med.voll.api.controller.domain.direccion.DatosDireccion;

public record DatosRespuestaMedico(
        long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        Especialidad especialidad,

        DatosDireccion direccion
) {
    public static DatosRespuestaMedico     from(Medico medico) {
        return new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getDocumento(),
                medico.getEspecialidad(),
                new DatosDireccion(medico.getDirecion().getCalle(),
                        medico.getDirecion().getDistrito(),
                        medico.getDirecion().getCiudad(),
                        medico.getDirecion().getNumero(),
                        medico.getDirecion().getComplemento())
        );
    }
}
