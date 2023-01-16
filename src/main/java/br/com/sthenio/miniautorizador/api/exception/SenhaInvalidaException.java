package br.com.sthenio.miniautorizador.api.exception;

public class SenhaInvalidaException extends RuntimeException{

    public SenhaInvalidaException(String s) {
        super(s);
    }
}
