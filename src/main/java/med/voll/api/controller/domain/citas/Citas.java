package med.voll.api.controller.domain.citas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controller.domain.medico.Especialidad;
import med.voll.api.controller.domain.medico.Medico;
import med.voll.api.controller.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "citas")
@Entity(name = "Cita")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Citas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico")
    private Medico medico;

    private LocalDateTime fecha;
    private Especialidad especialidad;

    public Citas(Paciente paciente, Medico medico, LocalDateTime fecha, Especialidad especialidad) {
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.especialidad = especialidad;


    }
}
