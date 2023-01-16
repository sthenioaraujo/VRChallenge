package br.com.sthenio.miniautorizador.api.exception;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(String s) {
        super(s);
    }
}
