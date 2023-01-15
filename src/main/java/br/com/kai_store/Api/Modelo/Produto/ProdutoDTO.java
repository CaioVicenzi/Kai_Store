package br.com.kai_store.Api.Modelo.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

// DTO DE PRODUTO
public record ProdutoDTO(
		@NotNull
		Long id,
		@NotBlank
		@Size(min = 2, max= 100, message = "Nome de usuário inválido")
		String nome, 
		@NotBlank
		String descricao,
		@NotNull
		@Positive
		Float preco,
		@NotBlank
		Categoria categoria) {
		
}
