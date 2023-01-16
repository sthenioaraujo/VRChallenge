package br.com.sthenio.miniautorizador.model;

import br.com.sthenio.miniautorizador.model.dto.form.CartaoFormDTO;
import br.com.sthenio.miniautorizador.model.dto.form.TransacaoFormDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@AllArgsConstructor
public class Transacao {

    private Cartao cartao;

    private BigDecimal valor;

    public static Transacao converteTransacao(TransacaoFormDTO transacaoForm) {
        Cartao cartao = Cartao.converteDTO(new CartaoFormDTO(transacaoForm.getNumeroCartao(), transacaoForm.getSenhaCartao()));
        return new Transacao(cartao, transacaoForm.getValor());
    }

    public boolean validarSaldoTransacao(){
        return this.getCartao().getSaldo().subtract(this.valor).compareTo(BigDecimal.ZERO)>-1;
    }

}
