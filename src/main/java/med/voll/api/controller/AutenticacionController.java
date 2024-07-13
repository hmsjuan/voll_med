package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.domain.usuario.DatosAutenticacion;
import med.voll.api.controller.domain.usuario.Usuario;
import med.voll.api.controller.infra.security.DatosJWTToken;
import med.voll.api.controller.infra.security.TokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServices tokenServices;



    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid
                                                DatosAutenticacion datosAutenticacion) {

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacion.login(),
                datosAutenticacion.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);


       // authenticationManager.authenticate(authenticationToken);
        var JWTtoken = tokenServices.generarTeToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
