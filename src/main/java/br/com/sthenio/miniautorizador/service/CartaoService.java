package br.com.sthenio.miniautorizador.service;

import br.com.sthenio.miniautorizador.api.exception.CartaoEmConflitoException;
import br.com.sthenio.miniautorizador.api.exception.CartaoNaoEncontradoException;
import br.com.sthenio.miniautorizador.model.Cartao;
import br.com.sthenio.miniautorizador.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    public Cartao pegaSaldoCartao(String numeroCartao) {

        return cartaoRepository.findByNumeroCartao(numeroCartao)
                .orElseThrow(()->
                        new CartaoNaoEncontradoException("Número de cartão informado não está cadastrado"));
    }

    public Cartao adicionarCartao(Cartao cartao) {

        cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao())
                .ifPresent( cartaoEncontrado -> {
                    throw new CartaoEmConflitoException("Número do cartão informado já cadastrado", cartaoEncontrado);
                });

        cartao.setSaldo(BigDecimal.valueOf(500));
        return cartaoRepository.save(cartao);
    }

    public Cartao atualizarCartao(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }

}
