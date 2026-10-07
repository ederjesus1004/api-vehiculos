package dev.eder.vehiculos.exception;

// Dato único repetido → 409
public class RecursoDuplicadoException extends RuntimeException{

    public RecursoDuplicadoException(String mensaje){
        super(mensaje);
    }
}
