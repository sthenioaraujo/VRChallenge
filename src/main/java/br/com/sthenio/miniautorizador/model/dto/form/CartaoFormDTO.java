package br.com.sthenio.miniautorizador.model.dto.form;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class CartaoFormDTO {

    @NotNull
    private String numeroCartao;

    @NotNull
    private String senha;

}
