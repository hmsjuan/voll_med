package med.voll.api.controller.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.controller.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServices {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarTeToken(Usuario usuario) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(obtenerFechaDeExpiracion())
                    .sign(algorithm);

        } catch (JWTCreationException exception){
           throw new RuntimeException();
        }

    }

    public String getSubject(String token) {

        if (token == null) {
            throw new RuntimeException();
        }

        DecodedJWT veryfier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            veryfier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);
            veryfier.getSubject();

        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }

     if (veryfier.getSubject() == null) {
         throw new RuntimeException("Verifier inválido");
     }
     return veryfier.getSubject();
    }


    public Instant obtenerFechaDeExpiracion() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-04:00"));
    }



}
