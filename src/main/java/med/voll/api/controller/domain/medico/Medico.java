package med.voll.api.controller.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controller.domain.direccion.Direccion;


@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="idMedico")

public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direcion;

    private Boolean activo = true;


    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono = datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direcion = new Direccion(datosRegistroMedico.direccion());
    }

    public void actualizarInformacion(ActualizarMedico actualizarMedico) {
        if (actualizarMedico.nombre() != null) {
            this.nombre = actualizarMedico.nombre();
        }

        if (actualizarMedico.telefono() != null) {
            this.telefono = actualizarMedico.telefono();
        }

        if (actualizarMedico.direccion() != null) {
            this.direcion = direcion.actualizarDireccion(actualizarMedico.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }



}

