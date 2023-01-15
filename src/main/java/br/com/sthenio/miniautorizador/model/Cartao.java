package br.com.sthenio.miniautorizador.model;

import br.com.sthenio.miniautorizador.model.dto.form.CartaoFormDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;

    private String senha;

    private BigDecimal saldo;

    public Cartao(CartaoFormDTO cartaoFormDTO) {
        this.numeroCartao = cartaoFormDTO.getNumeroCartao();
        this.senha = cartaoFormDTO.getSenha();
        this.saldo = null;
    }

    public static Cartao converteDTO(CartaoFormDTO cartaoFormDTO) {
        return new Cartao(cartaoFormDTO);
    }

}
