package convertendo_datahora_texto.app;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        // ---------------------------------------------------------------
        // 1) INSTANCIAÇÃO: texto ISO 8601 -> objeto de data-hora
        //    O formato ISO 8601 é o padrão do Java: não precisa de formatador.
        // ---------------------------------------------------------------
        LocalDate d04 = LocalDate.parse("2022-07-20");                      // data local (sem hora e sem fuso)
        LocalDateTime d05 = LocalDateTime.parse("2022-07-20T01:30:26");     // data-hora local (sem fuso); o "T" separa data e hora
        Instant d06 = Instant.parse("2022-07-20T01:30:26Z");                // data-hora global; o "Z" indica UTC

        // ---------------------------------------------------------------
        // 2) FORMATADORES (DateTimeFormatter)
        //    Documentação com todas as letras dos padrões:
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html
        //
        //    Letras mais usadas:
        //      dd = dia | MM = mês (maiúsculo!) | yyyy = ano
        //      HH = hora em 24h | mm = minuto (minúsculo!) | ss = segundo
        // ---------------------------------------------------------------
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Instant não tem fuso. Por isso o formatador precisa de um fuso (withZone)
        // para saber em qual horário mostrar. Aqui usa o fuso da máquina.
        DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());

        // Formatadores prontos do padrão ISO 8601 (não precisam de ofPattern)
        DateTimeFormatter fmt4 = DateTimeFormatter.ISO_DATE_TIME;           // para data-hora local ou com fuso
        DateTimeFormatter fmt5 = DateTimeFormatter.ISO_INSTANT;             // para Instant

        // ---------------------------------------------------------------
        // 3) FORMATAÇÃO: data-hora -> texto
        //    Três jeitos de formatar um LocalDate com o mesmo resultado
        // ---------------------------------------------------------------
        System.out.println("d04 = " + d04.format(fmt1));                    // 20/07/2022 (objeto.format(formatador))
        System.out.println("d04 = " + fmt1.format(d04));                    // 20/07/2022 (formatador.format(objeto))
        System.out.println("d04 = " + d04.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));  // 20/07/2022 (formatador criado na hora)

        // LocalDateTime aceita formatadores só de data ou de data e hora
        System.out.println("d05 = " + d05.format(fmt1));                    // 20/07/2022 (só a data)
        System.out.println("d05 = " + d05.format(fmt2));                    // 20/07/2022 01:30 (os segundos ficam de fora)
        System.out.println("d05 = " + d05.format(fmt4));                    // 2022-07-20T01:30:26 (ISO 8601)

        // Instant: formatador com fuso (fmt3) mostra o horário local da máquina.
        // O resultado depende do fuso do seu computador. Em São Paulo (UTC-3): 19/07/2022 22:30
        System.out.println("d06 = " + fmt3.format(d06));

        // Instant em ISO 8601, sempre em UTC. Resultado: 2022-07-20T01:30:26Z
        System.out.println("d06 = " + fmt5.format(d06));

        // toString() do Instant já devolve o mesmo texto ISO 8601, sem precisar de formatador
        System.out.println("d06 = " + d06.toString());

        // ---------------------------------------------------------------
        // ATENÇÃO
        //  - fmt1.format(d06) lançaria UnsupportedTemporalTypeException:
        //    Instant não tem fuso, então não sabe qual dia/hora mostrar. Use withZone.
        //  - d04.format(fmt2) também daria erro: LocalDate não tem hora.
        //  - MM (mês) e mm (minuto) são diferentes. Trocar gera datas erradas sem avisar.
        // ---------------------------------------------------------------
    }
}
