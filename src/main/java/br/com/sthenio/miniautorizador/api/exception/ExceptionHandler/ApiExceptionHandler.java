package br.com.sthenio.miniautorizador.api.exception.ExceptionHandler;

import br.com.sthenio.miniautorizador.api.exception.CartaoEmConflitoException;
import br.com.sthenio.miniautorizador.api.exception.CartaoNaoEncontradoException;
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
        return handleExceptionGeneral(HttpStatus.UNPROCESSABLE_ENTITY, ErrosCartao.CARTAO_INEXISTENTE.toString());
    }

    private ResponseEntity<Object> handleExceptionGeneral(HttpStatus status, Object obj) {
        return ResponseEntity.status(status.value()).body(obj);
    }


}
