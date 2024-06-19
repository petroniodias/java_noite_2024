package br.com.cursojava.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursojava.cadastro.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
