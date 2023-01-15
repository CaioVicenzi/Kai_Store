package br.com.kai_store.Api.Modelo.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoDtoCadastro(
		@NotBlank
		@Size(min = 2, max= 100, message = "Nome de usuário inválido")
		String nome, 
		
		@NotBlank
		String descricao, 
		@NotNull
		Categoria categoria,
		
		@NotNull
		Float preco) {

}
