package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.controller.domain.medico.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody
                                                                @Valid
                                                                DatosRegistroMedico datosRegistroMedico,
                                                                UriComponentsBuilder uriComponentsBuilder) {

        Medico medico =  medicoRepository.save(new Medico(datosRegistroMedico));

        DatosRespuestaMedico datosRespuestaMedico =DatosRespuestaMedico.from(medico);

        URI url = uriComponentsBuilder
                .path("/medicos/{id}")
                .buildAndExpand(medico.getId())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaMedico);
        //debe retornar un 201 create
        //URL donde se puede ver el medico
        //GET http://localhost:8080/medicos/id

    }
    @GetMapping
    public Page<DatosListadoMedico> listarMedicos(@PageableDefault(size = 2) Pageable paginacion) {

        return medicoRepository.findAllByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid ActualizarMedico actualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(actualizarMedico.id());
        medico.actualizarInformacion(actualizarMedico);

        DatosRespuestaMedico datosRespuestaMedico =DatosRespuestaMedico.from(medico);

        return ResponseEntity.ok(datosRespuestaMedico);
    }

    @DeleteMapping("/{idMedico}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long idMedico) {
        Medico medico = medicoRepository.getReferenceById(idMedico);
        medico.desactivarMedico();

        return ResponseEntity.noContent().build();

    }
    @GetMapping("/{idMedico}")
    public ResponseEntity<DatosRespuestaMedico> retornarDatosMedico(@PathVariable Long idMedico) {

        Medico medico = medicoRepository.getReferenceById(idMedico);
        DatosRespuestaMedico datosRespuestaMedico =DatosRespuestaMedico.from(medico);
        return ResponseEntity.ok(datosRespuestaMedico);
    }




}

