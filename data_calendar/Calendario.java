package data_calendar;

 
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
 
// ATENÇÃO: Calendar também é API ANTIGA (legado). Em código novo, use java.time:
//   somar horas  -> dateTime.plusHours(4)
//   obter minuto -> dateTime.getMinute()
//   obter mês    -> dateTime.getMonthValue()
// Os resultados dos comentários valem para quem roda no Brasil (UTC-3).
public class Calendario {
 
    public static void main(String[] args) {
        somarUnidadeDeTempo();
        System.out.println("-------------");
        obterUnidadeDeTempo();
    }
 
    // ---------------------------------------------------------------
    // TÓPICO 1: SOMAR UMA UNIDADE DE TEMPO
    // Date não tem plusHours/plusDays. A soma passa por um Calendar.
    // ---------------------------------------------------------------
    static void somarUnidadeDeTempo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
 
        Date d = Date.from(Instant.parse("2018-06-25T15:42:07Z"));   // Z = UTC
        System.out.println(sdf.format(d));                           // 25/06/2018 12:42:07 (15:42:07Z - 3h)
 
        // Passo a passo da soma:
        Calendar cal = Calendar.getInstance();     // 1) cria o Calendar (fuso e idioma da máquina)
        cal.setTime(d);                            // 2) coloca o Date dentro dele
        cal.add(Calendar.HOUR_OF_DAY, 4);          // 3) soma 4 horas (use número negativo para subtrair)
        d = cal.getTime();                         // 4) tira o Date de volta: precisa guardar o resultado
 
        System.out.println(sdf.format(d));                           // 25/06/2018 16:42:07
 
        // Pontos importantes:
        //  - HOUR_OF_DAY = hora em 24h. HOUR = hora em 12h (cuidado, é outra coisa).
        //  - O add() cuida das viradas: somar 20 horas já passa para o dia seguinte.
        //  - Outras unidades: Calendar.MINUTE, Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR...
        //  - O Calendar é MUTÁVEL: o cal.add() altera o próprio objeto (ao contrário do plusDays do java.time).
    }
 
    // ---------------------------------------------------------------
    // TÓPICO 2: OBTER UMA UNIDADE DE TEMPO
    // Também passa pelo Calendar: get(campo) devolve um número.
    // ---------------------------------------------------------------
    static void obterUnidadeDeTempo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
 
        Date d = Date.from(Instant.parse("2018-06-25T15:42:07Z"));
        System.out.println(sdf.format(d));                           // 25/06/2018 12:42:07
 
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
 
        int minutes = cal.get(Calendar.MINUTE);                      // 42
        int month = 1 + cal.get(Calendar.MONTH);                     // 1 + 5 = 6
 
        System.out.println("Minutes: " + minutes);                   // Minutes: 42
        System.out.println("Month: " + month);                       // Month: 6
 
        // Pegadinha clássica: os MESES do Calendar começam em ZERO (janeiro = 0, junho = 5).
        // Por isso o "1 +" para mostrar o mês do jeito que a gente fala.
        // No java.time não tem isso: getMonthValue() já devolve 1 a 12.
        //
        // O resultado depende do fuso da máquina: aqui o minuto não muda, mas a hora mudaria
        // (cal.get(Calendar.HOUR_OF_DAY) daria 12 no Brasil e 15 em UTC).
    }
}
