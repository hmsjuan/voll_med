package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.controller.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPaciente> registrarPaciente(
            @RequestBody @Valid
            DatosRegistroPaciente datosRegistroPaciente,
            UriComponentsBuilder uriComponentsBuilder) {


        Paciente paciente =  pacienteRepository.save(new Paciente(datosRegistroPaciente));

        DatosRespuestaPaciente datosRespuestaPaciente =DatosRespuestaPaciente.from(paciente);

        URI url = uriComponentsBuilder
                .path("/pacientes/{id}")
                .buildAndExpand(paciente.getId())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaPaciente);
        //debe retornar un 201 create
        //URL donde se puede ver el medico
        //GET http://localhost:8080/medicos/id

    }

    @GetMapping
    public Page<DatosListadoPaciente> listarMedicos(@PageableDefault(size = 2) Pageable paginacion) {

        return pacienteRepository.findAllByActivoTrue(paginacion).map(DatosListadoPaciente::new);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> actualizarPaciente(@RequestBody @Valid ActualizarPaciente actualizarPaciente) {
       Paciente paciente= pacienteRepository.getReferenceById(actualizarPaciente.id());
        paciente.actualizarInformacion(actualizarPaciente);

        DatosRespuestaPaciente datosRespuestaPaciente =DatosRespuestaPaciente.from(paciente);

        return ResponseEntity.ok(datosRespuestaPaciente);
    }

    @DeleteMapping("/{idMedico}")
    @Transactional
    public ResponseEntity eliminarPaciente(@PathVariable Long idPaciente) {
        Paciente paciente = pacienteRepository.getReferenceById(idPaciente);
        paciente.desactivarPaciente();

        return ResponseEntity.noContent().build();

    }
    @GetMapping("/{idMedico}")
    public ResponseEntity<DatosRespuestaPaciente> retornarDatosMedico(@PathVariable Long idPaciente) {

        Paciente paciente= pacienteRepository.getReferenceById(idPaciente);
        DatosRespuestaPaciente datosRespuestaPaciente =DatosRespuestaPaciente.from(paciente);
        return ResponseEntity.ok(datosRespuestaPaciente);
    }


}
