# Aplicação de Quiz Avançado

Esta é uma aplicação Spring Boot que oferece uma plataforma de quiz avançada. A aplicação busca perguntas da Open Trivia Database, processa e as armazena em um banco de dados, permitindo que os usuários as acessem e respondam.

## Sumário
- [Introdução](#introdução)
- [Recursos](#recursos)
- [Endpoints](#endpoints)
- [DTOs e Modelos](#dtos-e-modelos)
- [Repositórios](#repositórios)
- [Serviços](#serviços)
- [Diagrama Mermaid](#diagrama-mermaid)

## Introdução
A aplicação utiliza o Spring Boot para criar uma API RESTful para gerenciar e fornecer perguntas de quiz avançado. Ela busca perguntas da API da Open Trivia Database, processa-as e as armazena em um banco de dados relacional. Os usuários podem acessar as perguntas por meio de vários endpoints.

## Recursos
- Busca perguntas da API da Open Trivia Database.
- Armazena perguntas e suas alternativas em um banco de dados relacional.
- Fornece endpoints para recuperar todas as perguntas, recuperar uma única pergunta por ID e salvar novas perguntas.

## Endpoints
### 1. Obter Todas as Perguntas
   - **Endpoint:** `/questions/all`
   - **Método:** `GET`
   - **Descrição:** Recupera todas as perguntas armazenadas no banco de dados.

### 2. Obter Uma Pergunta por ID
   - **Endpoint:** `/questions/{id}`
   - **Método:** `GET`
   - **Descrição:** Recupera uma única pergunta pelo seu ID.

### 3. Salvar Perguntas
   - **Endpoint:** `/questions`
   - **Método:** `POST`
   - **Descrição:** Busca perguntas da Open Trivia Database, processa-as e as salva no banco de dados.

## DTOs e Modelos
### 1. JsonQuestionDTO
   - Representa a estrutura de uma pergunta em formato JSON obtida da Open Trivia Database.

### 2. Alternative
   - Representa uma alternativa para uma pergunta, incluindo uma descrição e se é correta.

### 3. Player
   - Representa um jogador participante do quiz, incluindo um apelido e pontuação.

### 4. Question
   - Representa uma pergunta, incluindo dificuldade, categoria, texto da pergunta, um conjunto de alternativas e o ID da alternativa correta.

### 5. User
   - Representa um usuário no sistema, incluindo um nome, nome completo e idade.

## Repositórios
### 1. AlternativeRepository
   - Gerencia a persistência de entidades de alternativa.

### 2. QuestionRepository
   - Gerencia a persistência de entidades de pergunta.

## Serviços
### 1. AlternativeService
   - Lida com a criação e persistência de entidades de alternativa.

### 2. QuestionJsonService
   - Recupera perguntas em formato JSON da API da Open Trivia Database.

### 3. QuestionService
   - Gerencia a recuperação e persistência de perguntas, integrando-se com `AlternativeService` e `QuestionJsonService`.

