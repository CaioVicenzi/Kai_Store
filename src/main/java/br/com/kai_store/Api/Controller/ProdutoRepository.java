package br.com.kai_store.Api.Controller;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kai_store.Api.Modelo.Produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
