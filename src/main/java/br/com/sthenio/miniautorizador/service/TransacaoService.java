package br.com.sthenio.miniautorizador.service;

import br.com.sthenio.miniautorizador.api.exception.SaldoInsuficienteException;
import br.com.sthenio.miniautorizador.api.exception.SenhaInvalidaException;
import br.com.sthenio.miniautorizador.model.Cartao;
import br.com.sthenio.miniautorizador.model.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransacaoService {

    @Autowired
    private CartaoService cartaoService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Transacao adicionarTransacao(Transacao transacao) {
        String senhaTransacao = transacao.getCartao().getSenha();
        Cartao cartao = cartaoService.pegaSaldoCartao(transacao.getCartao().getNumeroCartao());
        transacao.setCartao(cartao);

        if (!validarSenha(cartao.getSenha(), senhaTransacao)) throw new SenhaInvalidaException("Senha inválida");
        if (!validarSaldo(transacao)) throw new SaldoInsuficienteException("Saldo insuficiente");

        cartao.atualizarSaldo(transacao.getValor());
        cartaoService.atualizarCartao(cartao);

        return transacao;
    }

    private Boolean validarSenha(String senhaCartao, String senhaTransacao) {
        return senhaCartao.equals(senhaTransacao);
    }

    private Boolean validarSaldo(Transacao transacao) {
        return transacao.validarSaldoTransacao();
    }

}
