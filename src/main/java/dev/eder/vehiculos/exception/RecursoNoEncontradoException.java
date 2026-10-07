package dev.eder.vehiculos.exception;

// No existe → 404
public class RecursoNoEncontradoException extends RuntimeException{

    public RecursoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
