package br.com.aulajava.exemploapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aulajava.exemploapi.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    
}