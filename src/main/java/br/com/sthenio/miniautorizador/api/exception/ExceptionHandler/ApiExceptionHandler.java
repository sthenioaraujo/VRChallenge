package br.com.sthenio.miniautorizador.api.exception.ExceptionHandler;

import br.com.sthenio.miniautorizador.api.exception.CartaoEmConflitoException;
import br.com.sthenio.miniautorizador.api.exception.CartaoNaoEncontradoException;
import br.com.sthenio.miniautorizador.api.exception.SaldoInsuficienteException;
import br.com.sthenio.miniautorizador.api.exception.SenhaInvalidaException;
import br.com.sthenio.miniautorizador.model.dto.response.CartaoResponseDTO;
import br.com.sthenio.miniautorizador.model.dto.response.ErrosCartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CartaoEmConflitoException.class)
    public ResponseEntity<Object> handleCartaoEmConflitoException(CartaoEmConflitoException ex) {
        return handleExceptionGeneral(HttpStatus.UNPROCESSABLE_ENTITY, CartaoResponseDTO.converteResponseDTO(ex.getCartao()));
    }

    @ExceptionHandler(CartaoNaoEncontradoException.class)
    public ResponseEntity<Object> handleCartaoNaoEncontradoException(HttpServletRequest request) {
        return request.getRequestURL().toString().endsWith("/transacoes") ?
             handleExceptionGeneral(HttpStatus.UNPROCESSABLE_ENTITY, ErrosCartao.CARTAO_INEXISTENTE.toString()) :
             ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<Object> handleSaldoInsuficienteException() {
        return handleExceptionGeneral(HttpStatus.UNPROCESSABLE_ENTITY, ErrosCartao.SALDO_INSUFICIENTE.toString());
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<Object> handleSenhaInvalidaException() {
        return handleExceptionGeneral(HttpStatus.UNPROCESSABLE_ENTITY, ErrosCartao.SENHA_INVALIDA.toString());
    }

    private ResponseEntity<Object> handleExceptionGeneral(HttpStatus status, Object obj) {
        return ResponseEntity.status(status.value()).body(obj);
    }

}
