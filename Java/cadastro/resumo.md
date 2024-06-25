## Introdução

O desenvolvimento moderno de aplicaçõoes tem utilizado cada vez mas APIs. 
> API - Application Programming Interface - É um conjutno de funções e padrões de programação que fornecem acesso para aplicativos ou plataformas. Atualmente tem sido amplamente utilizadas em ambiente WEB por meio dos Web Services REST.

> O uso das APIs evitam que um desenvolvedor precise criar e instalar diferentes recursos para que sistemas ou aplicativos diferentes “conversem” entre si. Isso contribui para reduzir o tempo da integração e para liberar o uso da solução muito mais rápido. 

Serviços REST: web services que fornecem APIs paraacesso a serviços /operações na web:
- Utiliza o protocolo HTTP (verbos, cabeçallhos, códigos de respostas, etc.)
- Pode ter como retorno diferentes tipos e fornatos de dados. Sendo os mais comuns Json e XML.

--- 
## Ferramentas utilizadas

- Visual Studio Code
- Extensão do VSC: Java Extension Pack
- Extensão do VSC: Spring Boot Extension Pack
- Java SDK
- Servidor de banco de dados MySQL
- Insomnia

  
--- 
## Projeto de backend utilizando API com Spring Boot
Criar projeto no start Spring

Utilizar: 
```
    group: br.com.cursojava
    artefact: cadastro
    Dependencies:
        Spring Web
        Spring Boot DevTools
        Spring Data JPA
        Lombok
        MySQL Driver
```    
Abrir projeto no VSC
Na pasta src\main\java\br\com\cursojava\cadastro criar:
- Pasta model
- Pasta repository
- Pasta controller

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public Produto adicionar(@RequestBody Produto produto){
        return repositorio.save(produto);
    }   

    @PutMapping
    public Produto alterar(@RequestBody Produto produto){
        if(produto.getId() != null && produto.getId() > 0) {
            Produto produtoExistente = repositorio.findById(produto.getId()).orElse(null);
            if (produtoExistente != null) {
                produtoExistente.setDescricao(produto.getDescricao());
                produtoExistente.setUnidade(produto.getUnidade());
                produtoExistente.setPreco(produto.getPreco());

                return repositorio.save(produtoExistente);
            }
        }
        return null;
    }

    @DeleteMapping
    public String excluir(@RequestBody Produto produto){
            if(produto.getId()>0){
                repositorio.delete(produto);
                return "Produto removido com sucesso";
            }
        return "Produto não encontrado";
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

---
### Chamadas aos endpoints

Podemos utilizar o Insomnia ou Postman para acessar os dados da API criada.  

Selecionar o método (GET/POST/PUT...)
```
http://localhost:8080/produtos
```
Exemplo de JSON
```json
{
   	"id": 2,
	"descricao": "Xis Bagunca",
	"unidade": "Un",
	"preco": 35.0
}
```

---
## Projeto de frontend consumindo API criada com Spring Boot
