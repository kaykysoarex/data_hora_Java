
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        exercicio1();
        exercicio2();
        exercicio3();
        exercicio4();
        exercicio5();
    }

    // ---------------------------------------------------------------
    // EXERCÍCIO 1: criar a mesma data com parse e com of
    // ---------------------------------------------------------------
    static void exercicio1() {
        LocalDate d01 = LocalDate.parse("2026-01-09");     // parse: eu entrego um TEXTO
        LocalDate d02 = LocalDate.of(2026, 1, 9);          // of: eu entrego NÚMEROS (ano, mês, dia, sem zero à esquerda)

        System.out.println("Parse: " + d01);               // 2026-01-09
        System.out.println("Of: " + d02);                  // 2026-01-09

        // ATENÇÃO: d02.equals(d02) compara o objeto com ELE MESMO, então é sempre true.
        // O certo é comparar um com o outro:
        System.out.println("Equals: " + d01.equals(d02));  // true

        // Por que true? São dois objetos separados, mas o equals compara o CONTEÚDO
        // (mesmo ano, mês e dia). Por isso parse e of dão o mesmo resultado.
    }

    // ---------------------------------------------------------------
    // EXERCÍCIO 2: LocalDateTime e os segundos que não aparecem
    // ---------------------------------------------------------------
    static void exercicio2() {
        LocalDateTime d01 = LocalDateTime.parse("2026-01-09T07:05");

        System.out.println("Data e hora: " + d01);         // 2026-01-09T07:05

        // Por que não aparecem os segundos? Porque eu passei só horas e minutos.
        // Mesmo escrevendo "2026-01-09T07:05:00", o toString() do LocalDateTime
        // OMITE os segundos quando são zero. Só aparecem se forem diferentes de zero:
        LocalDateTime d02 = LocalDateTime.parse("2026-01-09T07:05:30");
        System.out.println("Com segundos: " + d02);        // 2026-01-09T07:05:30
    }

    // ---------------------------------------------------------------
    // EXERCÍCIO 3: formatar com yyyy e com yy
    // ---------------------------------------------------------------
    static void exercicio3() {
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd-MM-yy");   // o enunciado pedia traços

        LocalDate d01 = LocalDate.parse("2026-01-09");

        System.out.println("data: " + fmt1.format(d01));   // 09/01/2026
        System.out.println("data: " + fmt2.format(d01));   // 09-01-26

        // yyyy = ano completo (2026)
        // yy   = só os DOIS ÚLTIMOS dígitos do ano (26)
        // Ao LER um texto com yy, o Java assume o século 2000 ("26" vira 2026).
        //
        // O molde só muda a SAÍDA de texto. O objeto d01 continua guardando 2026-01-09.
    }

    static void exercicio4() {

        LocalDateTime d01 = LocalDateTime.parse("2026-01-09T07:05");

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy");

        System.out.println("data e hora formatada: " + fmt.format(d01));

    }

    static void exercicio5() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");   

        LocalDate d01 = LocalDate.parse("09/01/2026", fmt);

        System.out.println("data: " + d01);
    }
}
