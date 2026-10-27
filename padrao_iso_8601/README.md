# Estudos Java: Padrão ISO 8601

Anotações da aula sobre o **padrão ISO 8601** do módulo *Trabalhando com data-hora*, do curso de Programação Orientada a Objetos com Java, do professor Nélio Alves (DevSuperior).

## 📚 Conceitos

### O que é o ISO 8601?

É o padrão internacional para escrever **datas e horas em texto**. É o formato que bancos de dados, APIs e JSON costumam usar para trocar informações de data-hora.

### Por que usar?

- **Sem ambiguidade:** `03/04/2022` pode ser 3 de abril ou 4 de março, dependendo do país. Já `2022-04-03` só tem uma leitura.
- **Ordenável:** como vai do maior para o menor (ano, mês, dia, hora...), ordenar os textos em ordem alfabética também os ordena por data.
- **Universal:** funciona entre sistemas, linguagens e países diferentes.

### Anatomia de um texto ISO 8601

```
2022-07-23T14:52:09.254935-03:00
│    │  │  │  │  │  │      │
│    │  │  │  │  │  │      └─ fuso (offset em relação ao UTC)
│    │  │  │  │  │  └──────── fração de segundo (opcional)
│    │  │  │  │  └─────────── segundos
│    │  │  │  └────────────── minutos
│    │  │  └───────────────── hora (24h)
│    │  └──────────────────── dia
│    └─────────────────────── mês
└──────────────────────────── ano
```

- O **`T`** separa a data da hora.
- O **`Z`** no final significa UTC (também chamado de Zulu time).
- O **`-03:00`** (ou `+01:00`, etc.) indica a diferença em relação ao UTC.

### Formatos de data-[hora] local (sem fuso)

| Exemplo                    | O que representa          |
| -------------------------- | ------------------------- |
| `2022-07-21`               | Só a data                 |
| `2022-07-21T14:52`         | Data e hora, sem segundos |
| `2022-07-22T14:52:09`      | Data, hora e segundos     |
| `2022-07-22T14:52:09.4073` | Com fração de segundo     |

### Formatos de data-hora global (com fuso)

| Exemplo                       | O que representa                                |
| ----------------------------- | ----------------------------------------------- |
| `2022-07-23T14:52:09Z`        | Momento exato em UTC                            |
| `2022-07-23T14:52:09.254935Z` | UTC com fração de segundo                       |
| `2022-07-23T14:52:09-03:00`   | Horário com offset (aqui, 3 horas atrás do UTC) |

O último exemplo é o mesmo momento que `2022-07-23T17:52:09Z`: basta somar o offset (`14:52 + 3h = 17:52`).

## 💻 Exemplos de código

### Texto ISO 8601 para objeto (instanciação)

O formato ISO 8601 é o **padrão do Java**: não precisa criar um `DateTimeFormatter` para esses textos.

```java
LocalDate d1 = LocalDate.parse("2022-07-21");
LocalDateTime d2 = LocalDateTime.parse("2022-07-21T14:52");
LocalDateTime d3 = LocalDateTime.parse("2022-07-22T14:52:09");
LocalDateTime d4 = LocalDateTime.parse("2022-07-22T14:52:09.4073");

Instant i1 = Instant.parse("2022-07-23T14:52:09Z");
Instant i2 = Instant.parse("2022-07-23T14:52:09.254935Z");
```

### Texto com offset

```java
// Funciona em Java 8 ou superior
Instant i3 = OffsetDateTime.parse("2022-07-23T14:52:09-03:00").toInstant();
System.out.println(i3);                              // 2022-07-23T17:52:09Z
```

> A partir do **Java 12**, `Instant.parse` também aceita textos com offset. Em versões anteriores, use `OffsetDateTime.parse(...).toInstant()` como acima.

### Objeto para texto ISO 8601

```java
LocalDate data = LocalDate.of(2022, 7, 21);
LocalDateTime dataHora = LocalDateTime.of(2022, 7, 22, 14, 52, 9);
Instant momento = Instant.parse("2022-07-23T14:52:09Z");

System.out.println(data);                            // 2022-07-21
System.out.println(dataHora);                        // 2022-07-22T14:52:09
System.out.println(momento);                         // 2022-07-23T14:52:09Z
```

O `toString()` desses tipos já devolve o texto em ISO 8601.

### Usando os formatadores prontos

```java
String t1 = DateTimeFormatter.ISO_LOCAL_DATE.format(data);          // 2022-07-21
String t2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dataHora); // 2022-07-22T14:52:09
String t3 = DateTimeFormatter.ISO_INSTANT.format(momento);          // 2022-07-23T14:52:09Z
```

## ⚠️ Pontos de atenção

- O `toString()` de `LocalDateTime` **omite os segundos quando são zero**: `LocalDateTime.of(2022, 7, 21, 14, 52)` imprime `2022-07-21T14:52`. O formatador `ISO_LOCAL_DATE_TIME` imprime `2022-07-21T14:52:00`.
- Um texto sem fuso (`2022-07-22T14:52:09`) **não pode** virar `Instant` diretamente, porque falta a informação de timezone. É preciso informar um fuso para converter.
- O `Z` e o offset **não são enfeite**: mudam o momento exato representado.
- Para formatos como `dd/MM/yyyy`, que **não** são ISO 8601, é preciso usar `DateTimeFormatter.ofPattern(...)`.

## 🎯 Objetivo

Reconhecer e usar o padrão ISO 8601 para ler e escrever data-horas, entendendo a diferença entre textos locais e globais, para trabalhar com APIs e bancos de dados.

## 🛠️ Tecnologias

- Java (API `java.time`, versão 8+)
- Visual Studio Code
- Git e GitHub

## 📈 Progresso

Este repositório será atualizado conforme eu avançar nas aulas, resolver exercícios e reforçar os conhecimentos adquiridos.

---

**Projeto de estudos pessoal, Sistemas de Informação.**