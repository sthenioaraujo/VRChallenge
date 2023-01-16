package br.com.sthenio.miniautorizador.model.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
public class TransacaoFormDTO {

    @NotNull
    private String numeroCartao;

    @NotNull
    private String senhaCartao;

    @NotNull
    @Positive
    private BigDecimal valor;

}
