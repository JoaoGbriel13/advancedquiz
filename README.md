# Advanced Quiz - README

## Descrição

O Advanced Quiz é um jogo de perguntas e respostas com o estilo do programa "Show do Milhão". O objetivo do jogo é que o usuário acumule 1 milhão de pontos respondendo corretamente às perguntas.

## Funcionalidades

1. **Cadastro e Login de Usuários:**
   - Os usuários podem se cadastrar fornecendo um apelido (nickname) e uma senha.
   - O login é realizado comparando o apelido e a senha cadastrados.

2. **Perguntas e Alternativas:**
   - As perguntas são obtidas de uma API externa e salvas no banco de dados.
   - Cada pergunta possui um conjunto de alternativas, sendo uma correta e as demais incorretas.

3. **Gameplay:**
   - Os usuários podem iniciar um novo jogo, recebendo uma série de perguntas de diferentes dificuldades (fácil, médio e difícil).
   - A cada pergunta respondida corretamente, o usuário acumula pontos com base na dificuldade da pergunta.

4. **Pontuação:**
   - A pontuação é calculada conforme a dificuldade da pergunta:
     - Fácil: 1000 pontos
     - Médio: 10000 pontos
     - Difícil: 100000 pontos
   - A pontuação mais alta é atualizada a cada resposta correta.
   - Quando a pontuação atinge 5000, 50000 ou 500000 pontos, ela é dobrada.

5. **Vitória e Resultados:**
   - O jogo é vencido quando o usuário atinge 1 milhão de pontos.
   - O usuário pode visualizar o resultado após cada pergunta, indicando se acertou ou errou.

## Endpoints da API

1. **/player:**
   - **POST /save:** Cadastra um novo jogador.
   - **POST /login:** Realiza o login do jogador.
   - **GET /{id}:** Retorna um DTO de um jogador especifico por ID.
   - **GET /all: ** Retorna uma Lista DTO contendo todos os jogadores

2. **/questions:**
   - **GET /all:** Obtém todas as perguntas cadastradas.
   - **GET /{id}:** Obtém uma pergunta específica por ID.
   - **POST :** Salva novas perguntas obtidas da API externa.
     - **Instrução:** Antes de iniciar o jogo, execute esta requisição para carregar as perguntas da API.

3. **/gameplay:**
   - **GET /get-gameplay/{id}:** Obtém os detalhes de uma sessão de jogo.
   - **GET /activequestion/{gameplayId}:** Obtém a pergunta ativa de uma sessão de jogo.
   - **POST /start:** Inicia uma nova sessão de jogo para um jogador.
   - **POST /{gameplayId}/{alternativeId}:** Verifica se a alternativa selecionada está correta.

## Configurações

1. **Base64Config:**
   - Configuração para fornecer uma instância de Base64.

2. **CorsConfig:**
   - Configuração para permitir requisições do frontend, habilitando CORS.

## Criptografia da Senha

A senha do usuário é criptografada usando a biblioteca Apache Commons Codec e o algoritmo Base64. A classe `Base64Config` fornece uma instância do codificador e decodificador Base64.

Ao salvar a senha do usuário, ela é convertida para bytes e codificada usando o algoritmo Base64. Durante o processo de login, a senha fornecida pelo usuário é codificada da mesma forma e comparada com a senha armazenada no banco de dados.

Isso proporciona uma camada adicional de segurança, garantindo que as senhas não sejam armazenadas em texto simples no banco de dados.

## Modelos DTO

Existem DTOs (Data Transfer Objects) para os seguintes modelos:

- UserDTO
- GameplayDTO
- QuestionsDTO
- QuestionGameplayDTO

Estes DTOs são utilizados para transferir dados entre as camadas da aplicação.

## Observações

- O jogo possui lógica para dobrar a pontuação ao atingir 5000, 50000 e 500000 pontos.
- O código pode ser estendido e aprimorado para incluir mais funcionalidades e melhorar a experiência do usuário.
- O uso da requisição POST para salvar questões da API é essencial para carregar o banco de dados com perguntas antes de iniciar um jogo.
- Devido à carga ocasional na API externa, podem ocorrer esporadicamente erros 500 durante a obtenção de perguntas.

## Como Executar

1. Clone o repositório.
2. Certifique-se de ter um banco de dados configurado e ajuste as configurações do Spring Boot, se necessário.
3. Execute a aplicação Spring Boot.
4. Acesse os endpoints da API conforme a descrição acima.

Lembre-se de adaptar e ajustar o código conforme necessário para atender aos requisitos específicos do seu projeto.
