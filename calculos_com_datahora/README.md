# Estudos Java: Cálculos com Data-Hora

Anotações da aula sobre **cálculos com data-hora** do módulo *Trabalhando com data-hora*, do curso de Programação Orientada a Objetos com Java, do professor Nélio Alves (DevSuperior).

## 📚 Conteúdos estudados

### Duas operações principais

| Operação                    | Entrada                   | Saída     |
| --------------------------- | ------------------------- | --------- |
| **Somar ou subtrair tempo** | data-hora ± tempo         | data-hora |
| **Calcular duração**        | data-hora 1 e data-hora 2 | duração   |

### Os objetos são imutáveis

Métodos como `plusDays` e `minusDays` **não alteram** o objeto original: eles devolvem um **objeto novo**. Por isso é preciso guardar o resultado em uma variável.

```java
LocalDate d = LocalDate.parse("2022-07-20");
d.plusDays(7);                  // resultado jogado fora, d continua 2022-07-20
LocalDate proxima = d.plusDays(7);   // agora o resultado está guardado
```

### Somar e subtrair tempo: cada tipo tem o seu jeito

| Tipo            | Como somar e subtrair                                 | Exemplo                      |
| --------------- | ----------------------------------------------------- | ---------------------------- |
| `LocalDate`     | `plusDays`, `minusDays`, `plusMonths`, `plusYears`... | `d.plusDays(7)`              |
| `LocalDateTime` | os mesmos, e também `plusHours`, `plusMinutes`...     | `dt.minusDays(7)`            |
| `Instant`       | `plus` / `minus` com `ChronoUnit`                     | `i.plus(7, ChronoUnit.DAYS)` |

O `Instant` **não tem** `plusDays`. Ele aceita unidades de tempo fixo (segundos, minutos, horas e dias), mas não meses nem anos.

### Duração (`Duration`)

`Duration.between(inicio, fim)` calcula o tempo entre dois momentos, sempre como `fim - inicio`. Depois, o resultado é convertido na unidade desejada:

| Método         | Devolve  |
| -------------- | -------- |
| `toDays()`     | dias     |
| `toHours()`    | horas    |
| `toMinutes()`  | minutos  |
| `getSeconds()` | segundos |

## 💻 Exemplos de código

### Somar e subtrair

```java
LocalDate d04 = LocalDate.parse("2022-07-20");
LocalDateTime d05 = LocalDateTime.parse("2022-07-20T01:30:26");
Instant d06 = Instant.parse("2022-07-20T01:30:26Z");

LocalDate pastWeekDate = d04.minusDays(7);
LocalDate nextWeekDate = d04.plusDays(7);

LocalDateTime pastWeekLocalDate = d05.minusDays(7);
LocalDateTime nextWeekLocalDate = d05.plusDays(7);

Instant pastWeekInstant = d06.minus(7, ChronoUnit.DAYS);
Instant nextWeekInstant = d06.plus(7, ChronoUnit.DAYS);
```

Saída:

```
pastWeekDate = 2022-07-13
nextWeekDate = 2022-07-27
pastWeekLocalDate = 2022-07-13T01:30:26
nextWeekLocalDate = 2022-07-27T01:30:26
pastWeekInstant = 2022-07-13T01:30:26Z
nextWeekInstant = 2022-07-27T01:30:26Z
```

### Calcular duração

```java
// LocalDate não tem hora: converta com atStartOfDay()
Duration t1 = Duration.between(pastWeekDate.atStartOfDay(), d04.atStartOfDay());

// LocalDateTime e Instant funcionam direto
Duration t2 = Duration.between(pastWeekLocalDate, d05);
Duration t3 = Duration.between(pastWeekInstant, d06);

// Ordem invertida: duração negativa
Duration t4 = Duration.between(d06, pastWeekInstant);
```

Saída:

```
t1 dias = 7
t2 dias = 7
t3 dias = 7
t4 dias = -7
```

## ⚠️ Pontos de atenção

- **Guarde o resultado:** `d.plusDays(7)` sem atribuir a uma variável não muda nada.
- **`Duration.between` com `LocalDate`** lança `UnsupportedTemporalTypeException`, porque falta a hora. Use `atStartOfDay()`.
- **`Instant.plus(1, ChronoUnit.MONTHS)`** também lança `UnsupportedTemporalTypeException`. O `Instant` não aceita meses nem anos.
- **A ordem importa:** `Duration.between(a, b)` fica negativa se `b` for anterior a `a`.
- `Duration` mede **tempo exato** (horas, minutos, segundos). Sete dias aparecem como `PT168H` quando impressos.

## 🎯 Objetivo

Somar e subtrair tempo de datas e momentos, e calcular a duração entre duas data-horas, entendendo as diferenças de cada tipo (`LocalDate`, `LocalDateTime` e `Instant`).

## 🛠️ Tecnologias

- Java (API `java.time`, versão 8+)
- Visual Studio Code
- Git e GitHub

## 📈 Progresso

Este repositório será atualizado conforme eu avançar nas aulas, resolver exercícios e reforçar os conhecimentos adquiridos.

---

**Projeto de estudos pessoal, Sistemas de Informação.**