# CLAUDE.md — Sistema Semear

Fonte da verdade: leia `MODELO.md` antes de qualquer tarefa.

## Stack
- Java 17+, Maven, JUnit 5. Sem frameworks externos.
- Pacote base: `br.undb.semear` (dominio, servico, repositorio, excecao, util).

## Convenções
- Nomes de classes e métodos em português, sem acentos nos identificadores.
- Uma classe por arquivo; atributos `private`; construtores validam estado inválido.
- Regras de negócio ficam no domínio (ex.: capacidade da turma dentro de `Turma`), nunca na `Main`.
- Valores monetários com `BigDecimal`; arredondamento `HALF_UP` (ver seção "Decisões" do MODELO.md).
- Preços vêm da tabela por `Serie`, nunca digitados no contrato.

## Testes
- Cada regra RNxx tem pelo menos um teste JUnit nomeado `rnXX_descricao`.
- Rodar `mvn test` antes de encerrar qualquer etapa.

## LGPD (obrigatório)
- Dados SEMPRE sintéticos (CPF `000.000.000-00`, nomes fictícios). Nunca dados reais.
- Nunca imprimir `FichaSaude` nem autodeclaração étnico-racial em logs ou `toString()`.

## Escopo
- Não implementar nada da coluna "Fora (futuro)" do MODELO.md.
- Trabalhar UMA etapa do plano (seção 9) por vez e parar ao terminar.
- Em dúvida sobre regra ou inconsistência (seção 7), perguntar em vez de inventar.
