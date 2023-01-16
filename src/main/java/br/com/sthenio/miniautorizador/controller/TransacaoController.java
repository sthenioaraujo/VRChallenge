package br.com.sthenio.miniautorizador.controller;

import br.com.sthenio.miniautorizador.model.Transacao;
import br.com.sthenio.miniautorizador.model.dto.form.TransacaoFormDTO;
import br.com.sthenio.miniautorizador.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String adicionarTransacao(@Valid @RequestBody TransacaoFormDTO transacaoForm){
        transacaoService.adicionarTransacao(Transacao.converteTransacao(transacaoForm));
        return "OK";
    }
}
