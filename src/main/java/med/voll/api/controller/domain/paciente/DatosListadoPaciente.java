package med.voll.api.controller.domain.paciente;


public record DatosListadoPaciente(
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email

) {
    public DatosListadoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(),
                paciente.getTelefono(),
                paciente.getDocumento(),
                paciente.getEmail());
    }

}

