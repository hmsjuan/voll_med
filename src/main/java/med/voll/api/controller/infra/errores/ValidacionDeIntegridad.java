package med.voll.api.controller.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException{

    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}

