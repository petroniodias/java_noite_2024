package br.com.cursojava.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursojava.cadastro.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
