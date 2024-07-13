package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.controller.domain.citas.AgendaDeCitasServices;
import med.voll.api.controller.domain.citas.CitasRepository;
import med.voll.api.controller.domain.citas.DatosDetalleCita;
import med.voll.api.controller.domain.citas.DatosAgendarCitas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/citas")
public class CitasController {


    @Autowired
    private AgendaDeCitasServices agendaDeCitasServices;

    @PostMapping
    @Transactional

    public ResponseEntity<DatosAgendarCitas> agendarCitas(
            @RequestBody @Valid DatosAgendarCitas datosAgendarCitas
    ) {

        agendaDeCitasServices.agendarCitas(datosAgendarCitas);



        return ResponseEntity.ok(new DatosAgendarCitas(
                null, null, null));

    }



}
