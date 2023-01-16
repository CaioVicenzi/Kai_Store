package br.com.kai_store.Api.infra;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErro {

	// O OBJETIVO DESSE METODO EH SIMPLESMENTE CAPTURAR UMA EXCESSAO DE ENTIDADE NAO ENCONTRADA E DEVOLVER UM ERRO 404 
	// PARA O CLIENTE (AO INVES DE UM ERRO DO SERVIDOR, QUE EH DEVOLVIDO POR PADRAO)
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratadorExcessaoEntityNotFound() {
		return ResponseEntity.notFound().build();
	}
	
	// O OBJETIVO DESSE METODO EH CAPTURAR UMA EXCESSAO DE ARGUMENTOS NAO VALIDOS E TRAZER UM ERRO PARA O CLIENTE (BAD REQUEST) 
	// COM OS CAMPOS QUE ELE DIGITOU ERRADO, JUNTO COM AS MENSAGENS DE CADA CAMPO
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratadorExcessaoValidacaoCampos(MethodArgumentNotValidException ex) {
		// pega todos os erros de campos e guarda em uma lista
		List<FieldError> lista = ex.getFieldErrors();
		// para cada erro de campo na lista, ele eh mapeado para um record do erro de campo que contem apenas
		// o campo e a mensagem de cada erro, esses objetos sao guardados dentro de uma lista
		List<ErroDeCampo> listaCampoMensagem = lista.stream().map(ErroDeCampo::new).toList();
		// essa lista eh enviada no corpo da resposta para o cliente
		return ResponseEntity.badRequest().body(listaCampoMensagem);
		
	}
	
	private record ErroDeCampo(String campo, String mensagem) {
		public ErroDeCampo(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
		
	}
}
