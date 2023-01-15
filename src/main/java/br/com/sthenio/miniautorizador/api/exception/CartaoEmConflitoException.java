package br.com.sthenio.miniautorizador.api.exception;

import br.com.sthenio.miniautorizador.model.Cartao;
import lombok.Getter;

@Getter
public class CartaoEmConflitoException extends RuntimeException {

    private final Cartao cartao;

    public CartaoEmConflitoException(String s, Cartao cartaoEncontrado) {
        super(s);
        this.cartao = cartaoEncontrado;
    }

}
