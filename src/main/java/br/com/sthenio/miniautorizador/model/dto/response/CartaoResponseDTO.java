package br.com.sthenio.miniautorizador.model.dto.response;

import br.com.sthenio.miniautorizador.model.Cartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@AllArgsConstructor
public class CartaoResponseDTO {

    @NotNull
    private String senha;

    @NotNull
    private String numeroCartao;

    public CartaoResponseDTO(Cartao cartao) {
        this.numeroCartao = cartao.getNumeroCartao();
        this.senha = cartao.getSenha();
    }

    public static CartaoResponseDTO converteResponseDTO(Cartao cartao) {
        return new CartaoResponseDTO(cartao);
    }

}
