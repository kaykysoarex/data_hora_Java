# Estudos Java: Timezone (Fuso Horário)

Anotações da aula sobre **timezone** do módulo *Trabalhando com data-hora*, do curso de Programação Orientada a Objetos com Java, do professor Nélio Alves (DevSuperior).

## 📚 Conceitos

### O que é timezone (fuso horário)?

É a regra que define **que horas são** em cada região do mundo no mesmo instante. O mesmo momento exato recebe horários diferentes dependendo do fuso.

### GMT, UTC e o "Z"

- **GMT** (Greenwich Mean Time): horário de Londres.
- **UTC** (Coordinated Universal Time): o padrão de referência mundial, usado como base para todos os outros fusos.
- **"Z"** (Zulu time): outra forma de escrever UTC. Aparece no final dos textos ISO 8601, como em `2022-07-23T14:30:00Z`.

### Fusos relativos ao UTC

| Região    | Offset                      |
| --------- | --------------------------- |
| São Paulo | UTC-3                       |
| Manaus    | UTC-4                       |
| Portugal  | UTC+1 (no horário de verão) |

### Offset x nome da timezone

|               | Exemplo             | Característica                                                  |
| ------------- | ------------------- | --------------------------------------------------------------- |
| **Offset**    | `-03:00`            | Diferença fixa em relação ao UTC. Não sabe de horário de verão. |
| **Nome (ID)** | `America/Sao_Paulo` | Região com todas as regras (inclusive mudanças de horário).     |

Por isso, em Java, **prefira o nome da timezone** ao offset fixo. Exemplos de nomes: `America/Sao_Paulo`, `Europe/Lisbon`, `US/Pacific`.

### Padrão ISO 8601 com fuso

| Formato         | Exemplo                       |
| --------------- | ----------------------------- |
| UTC (Z)         | `2022-07-23T14:52:09Z`        |
| UTC com frações | `2022-07-23T14:52:09.254935Z` |
| Com offset      | `2022-07-23T14:52:09-03:00`   |

### Boa prática: armazene UTC, mostre local

O banco e a API guardam o momento exato em UTC. A conversão para o fuso do usuário acontece só na hora de **exibir**.

O mesmo instante `2022-07-23T14:30:00Z` em diferentes lugares:

| Local     | Timezone                            | Horário exibido |
| --------- | ----------------------------------- | --------------- |
| São Paulo | `America/Sao_Paulo` (UTC-3)         | `11:30`         |
| Manaus    | `America/Manaus` (UTC-4)            | `10:30`         |
| Lisboa    | `Europe/Lisbon` (UTC+1 no verão)    | `15:30`         |
| Londres   | `Europe/London` (UTC+1 no verão)    | `15:30`         |
| Nova York | `America/New_York` (UTC-4 no verão) | `10:30`         |
| Tóquio    | `Asia/Tokyo` (UTC+9)                | `23:30`         |

> **Observação:** em julho, o Reino Unido está no horário de verão (UTC+1), então a hora em Londres seria 15:30, e não 14:30 como aparece no desenho do slide da aula. É um bom exemplo de por que usar nomes de timezone: o Java conhece essas regras.

## 💻 Exemplos de código

### Timezones em Java

```java
ZoneId saoPaulo = ZoneId.of("America/Sao_Paulo");
ZoneId lisboa = ZoneId.of("Europe/Lisbon");
ZoneId meuPc = ZoneId.systemDefault();               // fuso da máquina
```

### Listar todas as timezones disponíveis

```java
for (String zona : ZoneId.getAvailableZoneIds()) {
    System.out.println(zona);
}
```

### Converter data-hora global (UTC) para local

```java
Instant live = Instant.parse("2022-07-23T14:30:00Z");

LocalDateTime noBrasil = LocalDateTime.ofInstant(live, ZoneId.of("America/Sao_Paulo"));
LocalDateTime emPortugal = LocalDateTime.ofInstant(live, ZoneId.of("Europe/Lisbon"));
LocalDateTime emToquio = LocalDateTime.ofInstant(live, ZoneId.of("Asia/Tokyo"));

System.out.println(noBrasil);                        // 2022-07-23T11:30
System.out.println(emPortugal);                      // 2022-07-23T15:30
System.out.println(emToquio);                        // 2022-07-23T23:30
```

### Formatar um Instant em um fuso específico

```java
DateTimeFormatter fmt = DateTimeFormatter
        .ofPattern("dd/MM/yyyy HH:mm")
        .withZone(ZoneId.of("America/Sao_Paulo"));

System.out.println(fmt.format(live));                // 23/07/2022 11:30
```

### O horário de verão muda o offset

O mesmo fuso pode ter offsets diferentes ao longo do ano:

```java
Instant verao = Instant.parse("2022-07-23T14:30:00Z");
Instant inverno = Instant.parse("2022-01-23T14:30:00Z");
ZoneId nyc = ZoneId.of("America/New_York");

System.out.println(LocalDateTime.ofInstant(verao, nyc));    // 2022-07-23T10:30 (UTC-4)
System.out.println(LocalDateTime.ofInstant(inverno, nyc));  // 2022-01-23T09:30 (UTC-5)
```

### Extra: ZonedDateTime e offset em texto

Além do que o módulo mostra, o Java tem tipos que guardam o fuso junto com a data-hora:

```java
ZonedDateTime z = live.atZone(ZoneId.of("America/Sao_Paulo"));
System.out.println(z);                               // 2022-07-23T11:30-03:00[America/Sao_Paulo]

// Texto com offset para Instant (UTC)
Instant convertido = OffsetDateTime.parse("2022-07-23T14:52:09-03:00").toInstant();
System.out.println(convertido);                      // 2022-07-23T17:52:09Z
```

## ⚠️ Pontos de atenção

- `Instant` é **sempre UTC**. O fuso só entra ao converter ou formatar para exibir.
- Use **nomes de timezone** (`America/Sao_Paulo`), não offsets fixos, quando precisar das regras de horário de verão.
- Evite abreviações como `EST` ou `BRT`: são ambíguas e não representam uma região de forma confiável.
- `ZoneId.systemDefault()` depende da máquina. Em servidores, o fuso pode ser diferente do seu computador, então no backend o ideal é trabalhar com UTC e converter só na exibição.

## 🎯 Objetivo

Entender como funcionam os fusos horários, converter data-horas globais para o horário local de qualquer região e aplicar a regra **"armazene UTC, mostre local"** nos meus projetos.

## 🛠️ Tecnologias

- Java (API `java.time`, versão 8+)
- Visual Studio Code
- Git e GitHub

## 📈 Progresso

Este repositório será atualizado conforme eu avançar nas aulas, resolver exercícios e reforçar os conhecimentos adquiridos.

---

**Projeto de estudos pessoal, Sistemas de Informação.**