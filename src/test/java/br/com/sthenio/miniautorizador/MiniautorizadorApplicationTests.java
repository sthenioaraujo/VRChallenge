package br.com.sthenio.miniautorizador;

import br.com.sthenio.miniautorizador.model.Cartao;
import br.com.sthenio.miniautorizador.model.Transacao;
import br.com.sthenio.miniautorizador.model.dto.form.CartaoFormDTO;
import br.com.sthenio.miniautorizador.model.dto.form.TransacaoFormDTO;
import br.com.sthenio.miniautorizador.model.dto.response.CartaoResponseDTO;
import br.com.sthenio.miniautorizador.model.dto.response.ErrosCartao;
import br.com.sthenio.miniautorizador.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MiniautorizadorApplicationTests {

	private static final String numeroCartao = "6549873025634501";
	private static final String senha = "1234";

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private CartaoRepository cartaoRepository;

	Cartao cartao;
	Transacao transacao;

	@BeforeAll
	public void inicioTeste() {
		this.cartao = Cartao.builder()
				.numeroCartao(numeroCartao)
				.senha(senha)
				.saldo(BigDecimal.valueOf(500))
				.build();

		this.transacao = new Transacao(this.cartao, BigDecimal.valueOf(10));
	}

	@Test
	@Order(1)
	public void criarCartaoSucesso() {
		CartaoFormDTO cartaoForm = new CartaoFormDTO(numeroCartao, senha );
		HttpEntity<CartaoFormDTO> httpEntity = new HttpEntity<>(cartaoForm);

		ResponseEntity<CartaoResponseDTO> response = this.testRestTemplate
				.exchange("/cartoes", HttpMethod.POST, httpEntity, CartaoResponseDTO.class);


		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertEquals(response.getBody().getNumeroCartao(), numeroCartao);
		assertEquals(response.getBody().getSenha(), senha);
	}

	@Test
	@Order(2)
	public void criarCartaoJaExistente() {
		CartaoFormDTO cartaoForm = new CartaoFormDTO(numeroCartao, senha );
		HttpEntity<CartaoFormDTO> httpEntity = new HttpEntity<>(cartaoForm);

		ResponseEntity<CartaoResponseDTO> response = this.testRestTemplate
				.exchange("/cartoes", HttpMethod.POST, httpEntity, CartaoResponseDTO.class);


		assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
		assertEquals(response.getBody().getNumeroCartao(), numeroCartao);
		assertEquals(response.getBody().getSenha(), senha);
	}

	@Test
	@Order(3)
	public void buscarCartaoPorNumero() {
		HttpEntity<String> httpEntity = new HttpEntity<>(numeroCartao);
		ResponseEntity<BigDecimal> response = this.testRestTemplate
				.exchange("/cartoes/" + numeroCartao, HttpMethod.GET, httpEntity, BigDecimal.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), new BigDecimal(500).setScale(2, RoundingMode.DOWN));
	}

	@Test
	@Order(4)
	public void buscarCartaoPorNumeroInexistente() {
		HttpEntity<String> httpEntity = new HttpEntity<>(numeroCartao + "1234");
		ResponseEntity<BigDecimal> response = this.testRestTemplate
				.exchange("/cartoes/" + numeroCartao + "1234", HttpMethod.GET, httpEntity, BigDecimal.class);

		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Test
	@Order(5)
	public void criarTransacaoSucesso() {
		TransacaoFormDTO transacaoForm = new TransacaoFormDTO(numeroCartao, senha, new BigDecimal(10));
		HttpEntity<TransacaoFormDTO> httpEntity = new HttpEntity<>(transacaoForm);

		ResponseEntity<String> response = this.testRestTemplate
				.exchange("/transacoes", HttpMethod.POST, httpEntity, String.class);

		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertEquals(response.getBody(), "OK");
	}


	@Test
	@Order(6)
	public void criarTransacaoSenhaInvalida() {
		TransacaoFormDTO transacaoForm = new TransacaoFormDTO(numeroCartao, senha + "1", new BigDecimal(10));
		HttpEntity<TransacaoFormDTO> httpEntity = new HttpEntity<>(transacaoForm);

		ResponseEntity<String> response = this.testRestTemplate
				.exchange("/transacoes", HttpMethod.POST, httpEntity, String.class);

		assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
		assertEquals(response.getBody(), ErrosCartao.SENHA_INVALIDA.toString());
	}

	@Test
	@Order(7)
	public void criarTransacaoSaldoInsuficiente() {
		TransacaoFormDTO transacaoForm = new TransacaoFormDTO(numeroCartao, senha, new BigDecimal(700));
		HttpEntity<TransacaoFormDTO> httpEntity = new HttpEntity<>(transacaoForm);

		ResponseEntity<String> response = this.testRestTemplate
				.exchange("/transacoes", HttpMethod.POST, httpEntity, String.class);

		assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
		assertEquals(response.getBody(), ErrosCartao.SALDO_INSUFICIENTE.toString());
	}

	@Test
	@Order(8)
	public void criarTransacaoCartaoInexistente() {
		TransacaoFormDTO transacaoForm = new TransacaoFormDTO(numeroCartao + "1", senha, new BigDecimal(10));
		HttpEntity<TransacaoFormDTO> httpEntity = new HttpEntity<>(transacaoForm);

		ResponseEntity<String> response = this.testRestTemplate
				.exchange("/transacoes", HttpMethod.POST, httpEntity, String.class);

		assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
		assertEquals(response.getBody(), ErrosCartao.CARTAO_INEXISTENTE.toString());
	}


}
