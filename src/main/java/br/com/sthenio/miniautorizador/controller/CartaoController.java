package br.com.sthenio.miniautorizador.controller;

import br.com.sthenio.miniautorizador.model.Cartao;
import br.com.sthenio.miniautorizador.model.dto.form.CartaoFormDTO;
import br.com.sthenio.miniautorizador.model.dto.response.CartaoResponseDTO;
import br.com.sthenio.miniautorizador.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartaoResponseDTO adicionarCartao(@Valid @RequestBody CartaoFormDTO cartaoForm) {
        Cartao cartao = cartaoService.adicionarCartao(Cartao.converteDTO(cartaoForm));
        return CartaoResponseDTO.converteResponseDTO(cartao);
    }

    @GetMapping("/{numeroCartao}")
    public BigDecimal pegaSaldoCartao(@PathVariable String numeroCartao) {
        return cartaoService.pegaSaldoCartao(numeroCartao).getSaldo();
    }

}
