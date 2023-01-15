package br.com.kai_store.Api.Modelo.Produto;

import jakarta.validation.constraints.NotNull;

// DTO DE PRODUTO PARA SER USADO NA OPERACAO DE ATUALIZACAO DE UM REGISTRO
public record ProdutoDtoAtualizacao(
		@NotNull
		Long id, 
		String descricao, 
		Float preco) {

}
