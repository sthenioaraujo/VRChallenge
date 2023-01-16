package br.com.sthenio.miniautorizador.api.exception;

public class CartaoNaoEncontradoException extends RuntimeException {

    public CartaoNaoEncontradoException(String s) {
        super(s);
    }
}
