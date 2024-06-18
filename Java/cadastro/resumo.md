
Criar projeto no start Spring
Utilizar: 
    group: br.com.cursojava
    artefact: cadastro
    
Abrir projeto no VSC
Na pasta src\main criar:
- Pasta model
- Pasta controller
- Pasta repository

---
Arquivo: model\Produto.java
```java
package br.com.cursojava.cadastro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Produto {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    private String unidade;
    private Double preco;
}
```
---
Arquivo: repository\ProdutoRepository.java
```java
package br.com.cursojava.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursojava.cadastro.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
```
---
Arquivo: controller\ProdutoController.java
```java
package br.com.cursojava.cadastro.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.cursojava.cadastro.model.Produto;
import br.com.cursojava.cadastro.repository.ProdutoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository repositorio;

    @GetMapping
    public List<Produto> listar(){
        return repositorio.findAll();
    }    
}
```
---
Arquivo: resources\application.properties
```text
spring.datasource.url=jdbc:mysql://localhost:3306/exemplo-api
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```
