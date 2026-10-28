# Estudos Java: Operações com Data-Hora

Anotações da aula sobre **operações importantes com data-hora** do módulo *Trabalhando com data-hora*, do curso de Programação Orientada a Objetos com Java, do professor Nélio Alves (DevSuperior).

## 📚 Conteúdos estudados

### Visão geral

| Operação         | Entrada                                     | Saída                        |
| ---------------- | ------------------------------------------- | ---------------------------- |
| **Instanciação** | agora                                       | data-hora                    |
| **Instanciação** | texto ISO 8601                              | data-hora                    |
| **Instanciação** | texto em formato customizado                | data-hora                    |
| **Instanciação** | dia, mês, ano, [horário]                    | data-hora local              |
| **Formatação**   | data-hora                                   | texto ISO 8601               |
| **Formatação**   | data-hora                                   | texto em formato customizado |
| **Obter dados**  | data-hora local                             | dia, mês, ano, horário       |
| **Converter**    | data-hora global + timezone (sistema local) | data-hora local              |
| **Cálculo**      | data-hora ± tempo                           | data-hora                    |
| **Cálculo**      | data-hora 1 e data-hora 2                   | duração                      |

**Instanciar** é criar o objeto de data-hora. **Formatar** é transformar o objeto em texto. **Converter** muda o fuso em que um momento é visto. **Calcular** soma, subtrai ou mede o tempo entre data-horas.

### Tipos usados

| Tipo                | O que representa                                   |
| ------------------- | -------------------------------------------------- |
| `LocalDate`         | Data local (sem hora e sem fuso)                   |
| `LocalDateTime`     | Data e hora locais (sem fuso)                      |
| `Instant`           | Momento exato global (UTC)                         |
| `DateTimeFormatter` | Define o formato do texto, na leitura e na escrita |
| `ZoneId`            | Timezone (por exemplo, `America/Sao_Paulo`)        |
| `Duration`          | Tempo decorrido entre duas data-horas              |
| `ChronoUnit`        | Unidades de tempo (dias, horas, minutos...)        |

## 💻 Instanciação

### 1. Agora

```java
LocalDate d01 = LocalDate.now();
LocalDateTime d02 = LocalDateTime.now();
Instant d03 = Instant.now();                         // sempre em UTC
```

### 2. Texto ISO 8601 para data-hora

O formato ISO 8601 é o padrão do Java: não precisa de formatador.

```java
LocalDate d04 = LocalDate.parse("2022-07-20");
LocalDateTime d05 = LocalDateTime.parse("2022-07-20T01:30:26");
Instant d06 = Instant.parse("2022-07-20T01:30:26Z");
```

### 3. Texto em formato customizado para data-hora

Para qualquer formato que não seja ISO, é preciso informar um `DateTimeFormatter`.

```java
DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

LocalDate d07 = LocalDate.parse("20/07/2022", fmt1);
LocalDateTime d08 = LocalDateTime.parse("20/07/2022 01:30", fmt2);

System.out.println(d07);                             // 2022-07-20
System.out.println(d08);                             // 2022-07-20T01:30
```

### 4. Dia, mês, ano e horário para data-hora local

```java
LocalDate d09 = LocalDate.of(2022, 7, 20);
LocalDateTime d10 = LocalDateTime.of(2022, 7, 20, 1, 30);
LocalDateTime d11 = LocalDateTime.of(2022, 7, 20, 1, 30, 26);
```

## 💻 Formatação

### 1. Data-hora para texto ISO 8601

```java
System.out.println(d09);                             // 2022-07-20
System.out.println(d11);                             // 2022-07-20T01:30:26
System.out.println(d06);                             // 2022-07-20T01:30:26Z

// Ou, de forma explícita, com os formatadores prontos
System.out.println(DateTimeFormatter.ISO_LOCAL_DATE.format(d09));
System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(d11));
System.out.println(DateTimeFormatter.ISO_INSTANT.format(d06));
```

### 2. Data-hora para texto em formato customizado

```java
System.out.println(d09.format(fmt1));                // 20/07/2022
System.out.println(fmt1.format(d09));                // 20/07/2022 (mesmo resultado)
System.out.println(d10.format(fmt2));                // 20/07/2022 01:30
```

### 3. Formatando um Instant

O `Instant` não tem fuso, então o formatador precisa saber **em qual fuso** mostrar o horário:

```java
DateTimeFormatter fmtSp = DateTimeFormatter
        .ofPattern("dd/MM/yyyy HH:mm")
        .withZone(ZoneId.of("America/Sao_Paulo"));

DateTimeFormatter fmtUtc = DateTimeFormatter
        .ofPattern("dd/MM/yyyy HH:mm")
        .withZone(ZoneId.of("UTC"));

System.out.println(fmtSp.format(d06));               // 19/07/2022 22:30
System.out.println(fmtUtc.format(d06));              // 20/07/2022 01:30
```

## 🔤 Letras mais usadas nos padrões

| Letra  | Significado        | Exemplo    |
| ------ | ------------------ | ---------- |
| `dd`   | Dia do mês         | `20`       |
| `MM`   | Mês (número)       | `07`       |
| `yyyy` | Ano                | `2022`     |
| `HH`   | Hora (formato 24h) | `01`, `14` |
| `mm`   | Minutos            | `30`       |
| `ss`   | Segundos           | `26`       |

## 💻 Obter dados de uma data-hora local

Data-hora local para dia, mês, ano e horário:

```java
LocalDate d = LocalDate.parse("2022-07-20");

System.out.println(d.getDayOfMonth());               // 20
System.out.println(d.getMonthValue());               // 7
System.out.println(d.getYear());                     // 2022
System.out.println(d.getDayOfWeek());                // WEDNESDAY

LocalDateTime dt = LocalDateTime.parse("2022-07-20T01:30:26");

System.out.println(dt.getHour());                    // 1
System.out.println(dt.getMinute());                  // 30
System.out.println(dt.getSecond());                  // 26
```

`getMonthValue()` devolve o número do mês. `getMonth()` e `getDayOfWeek()` devolvem enums (`JULY`, `WEDNESDAY`).

## 💻 Converter data-hora global para local

Usa-se o `Instant` (global) e uma timezone (`ZoneId`). Se o objetivo for o fuso do computador, usa-se o fuso do sistema.

```java
Instant momento = Instant.parse("2022-07-20T01:30:26Z");

// Fuso do sistema local
LocalDateTime local = LocalDateTime.ofInstant(momento, ZoneId.systemDefault());

// Fusos específicos
LocalDateTime saoPaulo = LocalDateTime.ofInstant(momento, ZoneId.of("America/Sao_Paulo"));
LocalDateTime lisboa = LocalDateTime.ofInstant(momento, ZoneId.of("Europe/Lisbon"));

System.out.println(saoPaulo);                        // 2022-07-19T22:30:26
System.out.println(lisboa);                          // 2022-07-20T02:30:26

// Só a data (LocalDate.ofInstant existe a partir do Java 9)
LocalDate dataSp = LocalDate.ofInstant(momento, ZoneId.of("America/Sao_Paulo"));
System.out.println(dataSp);                          // 2022-07-19
```

Repare que o mesmo instante caiu em **dias diferentes** em São Paulo (19) e em Lisboa (20). Por isso o fuso importa.

Para ver os nomes de timezone disponíveis:

```java
for (String zona : ZoneId.getAvailableZoneIds()) {
    System.out.println(zona);
}
```

## 💻 Cálculos com data-hora

### Data-hora ± tempo

```java
LocalDate hoje = LocalDate.parse("2022-07-20");

LocalDate semanaPassada = hoje.minusDays(7);         // 2022-07-13
LocalDate proximaSemana = hoje.plusDays(7);          // 2022-07-27
LocalDate daquiA2Meses = hoje.plusMonths(2);         // 2022-09-20

LocalDateTime dt = LocalDateTime.parse("2022-07-20T01:30:26");
LocalDateTime maisTarde = dt.plusHours(5);           // 2022-07-20T06:30:26

// Com Instant, use ChronoUnit
Instant agora = Instant.parse("2022-07-20T01:30:26Z");
Instant daquiA7Dias = agora.plus(7, ChronoUnit.DAYS);       // 2022-07-27T01:30:26Z
Instant ha7Dias = agora.minus(7, ChronoUnit.DAYS);          // 2022-07-13T01:30:26Z
```

### Duas data-horas para duração

```java
// Com LocalDate: converta para data-hora com atStartOfDay()
Duration t1 = Duration.between(semanaPassada.atStartOfDay(), hoje.atStartOfDay());
System.out.println(t1.toDays());                     // 7
System.out.println(t1.toHours());                    // 168

// Com LocalDateTime e Instant: direto
Duration t2 = Duration.between(dt, maisTarde);
System.out.println(t2.toHours());                    // 5

Duration t3 = Duration.between(agora, daquiA7Dias);
System.out.println(t3.toDays());                     // 7

// Ordem invertida resulta em duração negativa
Duration t4 = Duration.between(daquiA7Dias, agora);
System.out.println(t4.toDays());                     // -7
```

`Duration` mede tempo em horas, minutos e segundos, e o `toString()` segue o ISO 8601: `t1` aparece como `PT168H`.

## ⚠️ Pontos de atenção

- **`MM` x `mm`:** maiúsculo é mês, minúsculo é minuto. Trocar gera datas erradas sem avisar.
- **`HH` x `hh`:** `HH` é 24 horas, `hh` é 12 horas.
- **`yyyy` x `YYYY`:** use `yyyy`. O `YYYY` representa o ano da semana e pode dar resultado errado perto da virada do ano.
- O texto precisa **bater com o padrão**. `LocalDate.parse("20/07/2022")` sem formatador lança `DateTimeParseException`.
- Datas inexistentes, como `LocalDate.of(2022, 2, 30)`, lançam `DateTimeException`.
- Formatar um `Instant` sem informar o fuso lança `UnsupportedTemporalTypeException`.
- `LocalDate` não tem hora: passar um texto com hora para `LocalDate.parse` também dá erro.
- `Duration.between` com `LocalDate` lança `UnsupportedTemporalTypeException`, porque falta a hora. Use `atStartOfDay()`.
- `Instant.plus(1, ChronoUnit.MONTHS)` também lança `UnsupportedTemporalTypeException`: o `Instant` aceita unidades de tempo fixo (como dias e horas), mas não meses ou anos.
- Na conversão para local, o **fuso muda o resultado**, inclusive o dia. Defina sempre qual fuso está usando.
- `Duration.between(a, b)` fica negativa se `b` for anterior a `a`.

## 🎯 Objetivo

Criar objetos de data-hora a partir de diferentes entradas, transformá-los em texto no formato desejado, extrair seus dados, converter momentos globais para o horário local e fazer cálculos com datas, entendendo quando usar o padrão ISO 8601 e quando usar um formato customizado.

## 🛠️ Tecnologias

- Java (API `java.time`, versão 8+)
- Visual Studio Code
- Git e GitHub

## 📈 Progresso

Este repositório será atualizado conforme eu avançar nas aulas, resolver exercícios e reforçar os conhecimentos adquiridos.

---

**Projeto de estudos pessoal, Sistemas de Informação.**