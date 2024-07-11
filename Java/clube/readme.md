# Clube Socio - Gerenciamento de Sócios e Atividades

Este projeto é uma aplicação web para gerenciar sócios e atividades de um clube. Utiliza Spring Boot no backend, Thymeleaf para renderização de templates no servidor, e HTML, CSS e JavaScript para o frontend.

## Tecnologias Utilizadas

- **Backend:**
  - Spring Boot
  - Spring Data JPA
  - MySQL (utilizando XAMPP para desenvolvimento)
  - Thymeleaf (para renderização de templates no servidor)
  
- **Frontend:**
  - HTML
  - CSS
  - JavaScript

## Estrutura do Projeto

- `src/main/java/br/com/cursojava/clube/controller`: Contém os controladores REST e Thymeleaf.
- `src/main/java/br/com/cursojava/clube/model`: Contém as classes de modelo.
- `src/main/java/br/com/cursojava/clube/repository`: Contém os repositórios Spring Data JPA.
- `src/main/resources/templates`: Contém os templates Thymeleaf.
- `src/main/resources/static`: Contém os arquivos estáticos (HTML, CSS, JS).

## Executando o Projeto

### Pré-requisitos

- JDK 17 ou superior
- Maven 3.6 ou superior
- XAMPP (para configurar o MySQL)

### Passos para Execução

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/seu-usuario/clube-socio.git
    cd clube-socio
    ```

2. **Configuração do Banco de Dados:**

    - Inicie o XAMPP e certifique-se de que o MySQL está rodando.
    - Crie um banco de dados no MySQL chamado `clube_socio`:
    
      ```sql
      CREATE DATABASE clube_socio;
      ```

    - Atualize as credenciais do MySQL em `src/main/resources/application.properties` se necessário:

      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/clube_socio
      spring.datasource.username=root
      spring.datasource.password=
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      ```

3. **Compile e execute o backend:**

    ```bash
    ./mvnw spring-boot:run
    ```

    O backend estará disponível em [http://localhost:8080](http://localhost:8080).

4. **Acesse a aplicação frontend:**

    A aplicação frontend está embutida no backend. Você pode acessar as páginas HTML diretamente:

    - **Páginas geradas com Thymeleaf:**
      - Página de Listagem de Sócios: [http://localhost:8080/web/socio](http://localhost:8080/web/socio)
      - Página de Formulário para Novo Sócio: [http://localhost:8080/web/socio/novo](http://localhost:8080/web/socio/novo)
      - Página de Detalhes do Sócio: [http://localhost:8080/web/socio/{id}](http://localhost:8080/web/socio/{id})
    
    - **Páginas estáticas:**
      - Página de Listagem de Sócios: [http://localhost:8080/socios.html](http://localhost:8080/socios.html)
      - Página de Listagem de Atividades: [http://localhost:8080/atividade.html](http://localhost:8080/atividade.html)

## Estrutura de Arquivos Importantes

- **Backend:**
  - `SocioController.java`: Controlador REST para sócios.
  - `AtividadeController.java`: Controlador REST para atividades.
  - `SocioWebController.java`: Controlador Thymeleaf para sócios.
  - `application.properties`: Configurações da aplicação, incluindo a configuração do banco de dados MySQL.

- **Frontend:**
  - `src/main/resources/static/socios.html`: Página HTML para listar, adicionar, editar e excluir sócios.
  - `src/main/resources/static/atividade.html`: Página HTML para listar, adicionar, editar e excluir atividades.
  - `src/main/resources/static/css/socio-styles.css`: Estilos CSS para a página de sócios.
  - `src/main/resources/static/css/atividade-styles.css`: Estilos CSS para a página de atividades.
  - `src/main/resources/static/js/socio-script.js`: JavaScript para manipulação da página de sócios.
  - `src/main/resources/static/js/atividade-script.js`: JavaScript para manipulação da página de atividades.

## Banco de Dados

O projeto utiliza o banco de dados MySQL, configurado através do XAMPP para desenvolvimento. Certifique-se de que o MySQL está rodando no XAMPP e que o banco de dados `clube_socio` está criado e configurado conforme instruções acima.

## Autor

- [Seu Nome](https://github.com/seu-usuario)

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
