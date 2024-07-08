package br.com.cursojava.clube.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursojava.clube.model.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long>{
    
}
