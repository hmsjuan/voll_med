package med.voll.api.controller.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controller.domain.direccion.Direccion;


@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")

public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Embedded
    private Direccion direcion;
    private Boolean activo = true;

    public Paciente(DatosRegistroPaciente datosRegistroPaciente) {
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.documento = datosRegistroPaciente.documento();
        this.direcion = new Direccion(datosRegistroPaciente.direccion());
    }



    public void actualizarInformacion(ActualizarPaciente actualizarPaciente) {
        if (actualizarPaciente.nombre() != null) {
            this.nombre = actualizarPaciente.nombre();
        }

        if (actualizarPaciente.telefono() != null) {
            this.telefono = actualizarPaciente.telefono();
        }

        if (actualizarPaciente.direccion() != null) {
            this.direcion = direcion.actualizarDireccion(actualizarPaciente.direccion());
        }
    }

    public void desactivarPaciente() {
        this.activo = false;
    }

}
