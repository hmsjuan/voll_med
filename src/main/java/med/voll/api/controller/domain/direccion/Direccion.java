package med.voll.api.controller.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Direccion {
    String calle;
    String distrito;
    String ciudad;
    String numero;
    String complemento;



    public Direccion(DatosDireccion datosDireccion) {
        this.calle = datosDireccion.calle();
        this.distrito = datosDireccion.distrito();
        this.ciudad = datosDireccion.ciudad();
        this.numero = datosDireccion.numero();
        this.complemento = datosDireccion.complemento();
    }

    public Direccion(String calle, String distrito, String ciudad, String numero, String complemento) {
    this.calle = calle;
    this.distrito = distrito;
    this.ciudad = ciudad;
    this.numero = numero;
    this.complemento = complemento;
    }


    @Override
    public String toString() {
        return "Calle " + calle+
                ", distrito " + distrito  +
                ", ciudad " + ciudad +
                ", #" + numero +
                " - " + complemento;
    }

    public Direccion actualizarDireccion(Direccion direccion) {
        this.calle = direccion.calle;
        this.distrito = direccion.distrito;
        this.ciudad = direccion.ciudad;
        this.numero = direccion.numero;
        this.complemento = direccion.complemento;

        return this;

    }
}
