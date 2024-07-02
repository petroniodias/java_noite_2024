package br.com.cursojava.clube.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursojava.clube.model.Socio;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    
}
