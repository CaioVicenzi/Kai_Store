package br.com.kai_store.Api.Modelo.Produto;

import br.com.kai_store.Api.Controller.ProdutoDtoAtualizacao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private Float preco;
		
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	// CONSTRUTOR SIMPLES QUE RECEBE UM DTO E GERA UM OBJETO DO TIPO PRODUTO BASEADO EM SEUS ATRIBUTOS
	public Produto(ProdutoDTO produtoCadastrado) {
		this.nome = produtoCadastrado.nome();
		this.categoria = produtoCadastrado.categoria();
		this.descricao = produtoCadastrado.descricao();
		this.preco = produtoCadastrado.preco();
	}
	
	public Produto() {
		super();
	}
	
	// METODO QUE CRIA GERA UM DTO DE UM OBJETO DO TIPO PRODUTO
	public ProdutoDTO criaDTO() {
		return new ProdutoDTO(this.id, this.nome, this.descricao, this.preco, this.categoria);
	}
	
	// RECEBE UM OBJETO DTO E ATUALIZA O PRODUTO BASEADO NOS ATRIBUTOS DO OBJETO RECEBIDO
	public void atualiza(ProdutoDtoAtualizacao produtoRecebido) {
		// verifica se cada um dos dados eh nulo, caso contrario, os valores sao atualizados
		if(produtoRecebido.descricao() != null) this.descricao = produtoRecebido.descricao();
		if(produtoRecebido.preco() != null) this.preco = produtoRecebido.preco();	
		
	}
	
	// METODOS GETTERS E SETTERS
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


}
