# Relatório de Acompanhamento da Equipe
## Projeto E-commerce Pedidos Break — Desenvolvimento Back-end (CSTADS601)

**Responsável pelo relatório:** Victor Hugo Dos Santos
**Data de referência:** 25/09/2026
**Início do projeto:** 07/08/2026 (aulas às sextas-feiras)

## 1. Contexto

Este relatório registra a mudança na composição da equipe e a frequência dos integrantes desde
o início do projeto, motivado pela perda do repositório antigo (com os commits originais da
equipe) e pela necessidade de deixar documentado, de forma formal, como o trabalho foi dividido
até aqui.

## 2. Composição original da equipe

| Nome | Situação atual |
|---|---|
| Victor Hugo Dos Santos | Ativo |
| Miguel Andrade Gallo | Ativo |
| Luiz Gustavo Vieira | Desligado do curso |

## 3. Frequência às aulas (sextas-feiras)

| Data | Aula | Victor Hugo | Miguel Gallo | Luiz Gustavo | Observação |
|---|---|---|---|---|---|
| 07/08/2026 | 01 | Presente | Presente | Presente | Início do projeto |
| 14/08/2026 | 02 | Presente | Presente | Presente | |
| 21/08/2026 | 03 | Presente | Presente | Presente | |
| 28/08/2026 | 04 | Presente | Presente | Presente | |
| 04/09/2026 | 05 | **Falta** | **Falta** | — | Único dia em que Victor e Miguel faltaram |
| 11/09/2026 | 06 | Presente | Presente | Saída do curso | Luiz sai do curso por volta desta data |
| 18/09/2026 | 07 | Presente | **Falta** | Desligado | Victor cria e apresenta sozinho o diagrama de relacionamentos |
| 25/09/2026 | 08 | Presente | Presente | Desligado | Data deste relatório |

## 4. Saída de Luiz Gustavo Vieira

Luiz Gustavo Vieira deixou o curso por volta do dia 11/09/2026, e a partir dessa data não
compareceu mais às aulas nem participou das entregas seguintes do projeto. A equipe segue,
portanto, com dois integrantes: Victor Hugo Dos Santos e Miguel Andrade Gallo.

## 5. Frequência de Victor Hugo e Miguel Gallo

- **Victor Hugo Dos Santos** esteve presente em todas as sextas-feiras, com exceção do dia
  04/09/2026.
- **Miguel Andrade Gallo** faltou em duas datas: 04/09/2026 e 18/09/2026 (última sexta-feira
  antes deste relatório).

Na sexta-feira de 18/09/2026, com a ausência de Miguel e a já confirmada saída de Luiz, o
diagrama de relacionamentos entre as classes do domínio (Pedido, ItemPedido, Produto, Cliente)
foi elaborado e apresentado individualmente por Victor Hugo.

## 6. Desenvolvimento do back-end

Nos dias em que Victor Hugo e Miguel Gallo estiveram presentes juntos, os dois trabalharam em
par na implementação do back-end do projeto, hoje reunido no repositório `ecommerce-pedidos-break`
(anexo). O estado atual do código contempla:

- **Domínio**: `Pessoa` (abstrata), `Cliente`, `Funcionario`, `Endereco`, `Produto`, `Pedido`,
  `ItemPedido` e o enum `SituacaoPedido`.
- **Formas de pagamento**: `FormaPagamento` (abstrata) e as implementações `Boleto`,
  `CartaoCredito` e `Pix`.
- **Utilitário**: `PedidoUtils`, com funções de apoio ao domínio.
- **Aplicação de demonstração**: `Aplicacao`, que comprova as regras de validação e os
  relacionamentos implementados.

Por conta da perda do repositório original, o histórico de commits foi reconstruído a partir de
um backup, o que faz com que o `git log` atual mostre poucos commits — todos registrados sob o
usuário de Victor Hugo — mesmo com a contribuição de Miguel tendo ocorrido em par, presencialmente,
nos dias em que os dois estiveram na aula. Este relatório existe justamente para deixar essa
divisão de trabalho registrada por escrito, já que ela não está refletida no histórico do Git.

## 7. Encaminhamentos

- A equipe segue com dois integrantes: Victor Hugo Dos Santos e Miguel Andrade Gallo.
- A partir deste relatório, o repositório passa a ter backup remoto ativo, evitando nova perda de
  histórico.
- A divisão de responsabilidades das próximas entregas será revista em função da saída de Luiz,
  redistribuindo entre os dois integrantes restantes as tarefas que antes seriam de três pessoas.
