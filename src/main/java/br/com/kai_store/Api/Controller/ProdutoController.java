package br.com.kai_store.Api.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kai_store.Api.Modelo.Produto.Produto;
import br.com.kai_store.Api.Modelo.Produto.ProdutoDTO;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	ProdutoRepository repository;
	
	// METODO QUE VAI RECEBER DADOS DE UM PRODUTO POR UMA REQUISICAO E VAI CADASTRAR UM PRODUTO NA TABELA DE PRODUTOS
	@PostMapping
	@Transactional
	public void adicionarProduto(@RequestBody ProdutoDTO produtoCadastrado){
		// criando um objeto do tipo Produto (que representa uma entidade)
		Produto produtoBanco = new Produto(produtoCadastrado);
		// salva o objetivo na tabela produtos
		repository.save(produtoBanco);
	}
	
	// METODO QUE RECUPERA UM PRODUTO NA TABELA DE PRODUTOS USANDO O ID DO PRODUTO (CHAVE PRIMARIA)
	@GetMapping("/{id}")
	public ProdutoDTO recuperarProduto(@PathVariable Long id) {
		// recupera um produto e devolve ele usando um dto
		Produto produtoRecuperado = repository.getReferenceById(id);
		return produtoRecuperado.criaDTO();
	}
	
	// METODO QUE RECUPERA TODOS OS PRODUTOS SALVOS NO BANCO DE DADOS
	@GetMapping
	public List<ProdutoDTO> recuperarProdutos() {
		List<ProdutoDTO> lista = new ArrayList<>();
		// para cada produto na tabela de produto, eh criado um dto desse produto e armazenado numa lista de DTO's
		repository.findAll().forEach((Produto p) -> lista.add(p.criaDTO()));
		
		// no fim a lista eh retornada
		return lista;
	}
	
	// METODO QUE DELETA UM REGISTRO DE PRODUTO PELO ID FORNECIDO A ELE PELA URL
	@DeleteMapping("/{id}")
	@Transactional
	public void deletaProduto(@PathVariable Long id) {
		// deleta um produto recuperado pelo id passado na url
		repository.delete(repository.getReferenceById(id));
	}
	
	// METODO QUE VAI RECEBER DADOS DE UM PRODUTO POR UMA REQUISICAO E VAI ATUALIZAR UM PRODUTO NA TABELA DE PRODUTOS
	@PutMapping
	@Transactional
	public void atualizaProduto(@RequestBody ProdutoDtoAtualizacao produtoRecebido) {
		Produto produtoAtualizar = repository.getReferenceById(produtoRecebido.id());
		produtoAtualizar.atualiza(produtoRecebido);
	}
}
