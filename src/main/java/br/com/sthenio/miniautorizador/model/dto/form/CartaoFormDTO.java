package br.com.sthenio.miniautorizador.model.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@AllArgsConstructor
public class CartaoFormDTO {

    @NotNull
    private String numeroCartao;

    @NotNull
    private String senha;

}
