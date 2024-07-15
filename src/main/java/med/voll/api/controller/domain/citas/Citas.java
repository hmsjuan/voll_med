package med.voll.api.controller.domain.citas;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;


    private LocalDateTime fecha;
/*
    @Column(name = "motivo_cancelacion")
    @Enumerated(EnumType.STRING)
    private MotivoCancelacion motivoCancelacion;
*/

    public Citas(Paciente paciente, Medico medico, LocalDateTime fecha) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
    }

}
