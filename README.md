# Aula 03 — Introdução a Data-Hora e Duração

## 📚 Objetivo da aula

Entender os conceitos básicos de data e hora, a diferença entre data-hora local e global, o funcionamento dos fusos horários e o conceito de duração em Java.

## 1. Conceitos importantes

### Data-hora local

Representa uma data ou horário sem informação de fuso horário.

Exemplos:
- Data de nascimento: `15/06/2001`
- Data e hora de uma venda: `13/08/2022 às 15:32`

É utilizada quando não é necessário considerar o horário de outras regiões.

### Data-hora global

Representa um momento específico no tempo, considerando a referência de fuso horário.

É importante em sistemas que precisam trabalhar com pessoas de diferentes regiões, como aplicações web.

Exemplo no padrão ISO 8601:

```text
2022-07-23T14:30:00Z
```

O `Z` indica que o horário está em UTC.

### Duração

Representa o tempo decorrido entre duas datas ou horários.

Exemplo:

```text
Início: 10:00
Fim:    12:30
Duração: 2 horas e 30 minutos
```

## 2. Fusos horários

Um fuso horário representa a diferença de horário entre determinadas regiões.

Referências apresentadas na aula:

- UTC/GMT: referência de tempo.
- São Paulo: GMT-3.
- Manaus: GMT-4.
- Portugal: GMT+1, conforme o exemplo do material.

Uma mesma data-hora global pode ser exibida em horários locais diferentes, dependendo do fuso horário.

## 3. Padrão ISO 8601

É um padrão utilizado para representar datas e horários de forma organizada.

Exemplos de data-hora local:

```text
2022-07-21
2022-07-21T14:52
2022-07-22T14:52:09
```

Exemplos de data-hora global:

```text
2022-07-23T14:52:09Z
2022-07-23T14:52:09-03:00
```

O `T` separa a data do horário, enquanto `Z` indica UTC. O sufixo `-03:00` indica uma diferença de três horas a menos em relação ao UTC.

## 4. Principais classes Java

O Java possui classes específicas para trabalhar com datas, horários e durações.

| Classe          | Finalidade                               |
| --------------- | ---------------------------------------- |
| `LocalDate`     | Representar uma data sem horário.        |
| `LocalDateTime` | Representar data e horário sem fuso.     |
| `Instant`       | Representar um instante global no tempo. |
| `Duration`      | Representar uma duração de tempo.        |
| `ZoneId`        | Identificar um fuso horário.             |
| `ChronoUnit`    | Trabalhar com unidades de tempo.         |

## 5. Operações com data-hora

As principais operações estudadas no material são:

- Criar uma data ou horário a partir do momento atual.
- Converter textos em objetos de data-hora.
- Formatar datas e horários como texto.
- Converter um instante global para o horário local.
- Obter dia, mês, ano e horário.
- Adicionar ou subtrair tempo.
- Calcular a duração entre dois momentos.

## 6. Boa prática

Uma recomendação apresentada na aula é **armazenar datas e horários em UTC e exibi-los no horário local quando necessário**.

Isso ajuda a manter a consistência em sistemas utilizados por pessoas de diferentes regiões.

## 📝 Resumo final

- **Data-hora local:** não contém informação de fuso horário.
- **Data-hora global:** representa um instante específico no tempo.
- **Duração:** representa o tempo decorrido entre dois momentos.
- **ISO 8601:** padrão para representar datas e horários.
- **Java:** oferece classes específicas para manipular datas, horários, fusos e durações.

---

**Curso:** Java  
**Aula:** 03 — Introdução a Data-Hora e Duração  
**Material de referência:** Trabalhando com data-hora — Nélio Alves.
