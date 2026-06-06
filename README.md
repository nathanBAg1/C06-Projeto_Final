# C06-Projeto_Final
Repositório para o Projeto Final da Matéria de C06

- Nome: João Pedro Torrano Dias; Matrícula: 273; Curso: GES.
- Nome: Nathan Bispo de Aguiar; Matrícula: 650; Curso: GES.
- Nome: Leonardo Cruz Zanin; Matrícula: 2169; Curso: GEC.

![Diagrama UML do Projeto](https://github.com/nathanBAg1/C06-Projeto_Final/blob/main/UML%20do%20Projeto%20de%20C06.png)

## Desenvolvimento assistido por Inteligência Artificial

Parte do desenvolvimento deste projeto contou com o apoio do agente de codificação
**Claude Code**, da Anthropic, utilizando o modelo **Claude Opus 4.8** (junho de 2026), além do ChatGPT da OpenAI.
A ferramenta foi empregada como recurso de apoio à programação (*pair programming*),
cabendo à equipe a modelagem, a revisão e a definição da versão final do código.

## Detalhes do Projeto

- **Herança e polimorfismo:** organização da hierarquia a partir da classe abstrata
  `Pessoa`, especializada por `Corretor`, `Supervisor` e `Vestibulando`, com sobrescrita
  dos métodos abstratos `confereSeusDadosEspecificos()` e `atualizaEmail()`.
- **Interfaces:** implementação de `ConsultarNota` pela classe `Vestibulando`, definindo o
  contrato de consulta de notas (`pesquisaNota()`).
- **Encapsulamento:** atributos privados acessados por métodos `get`/`set`, isolando o
  estado interno das entidades de domínio.
- **Tratamento de exceções:** emprego de exceções personalizadas
  (`NotaNaoEncontradaException`, `NotaInvalidaException`, `FalhaNoEmailException`, entre
  outras) para representar regras de negócio.
- **Concorrência (threads):** o lançamento de notas em lote
  (`Corretor.lancarNotasPorArquivo`) foi implementado com `Runnable`
  (`Thread.start()` / `join()`), executando as gravações em paralelo, cada thread com sua
  própria conexão.
- **Padrão DAO e separação de camadas:** o acesso ao banco de dados foi concentrado nas
  classes `*DAO`, mantendo as entidades de domínio independentes da camada de persistência.
- **Coleções:** uso de `List` e `Map` na manipulação de conjuntos de vestibulandos,
  vestibulares e matérias.
