package trabalhando_com_date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

// ATENÇÃO: Date e SimpleDateFormat são a API ANTIGA (legado).
// Em código novo, prefira java.time (Instant, LocalDateTime, DateTimeFormatter).
// Equivalências: Date ~ Instant | SimpleDateFormat ~ DateTimeFormatter
public class Main {

    // parse() lança ParseException (exceção verificada): o main precisa declarar "throws"
    public static void main(String[] args) throws ParseException {

        // ---------------------------------------------------------------
        // 1) FORMATADORES (SimpleDateFormat)
        //    Mesmas letras do DateTimeFormatter: dd, MM, yyyy, HH, mm, ss
        //    Por padrão, usam o FUSO DA MÁQUINA para ler e escrever.
        // ---------------------------------------------------------------
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // sdf3 fixado em GMT (= UTC): mostra o horário de referência, não o da máquina.
        // Chamadas como esta só podem ficar DENTRO de um método (no seu código estavam soltas na classe).
        sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));

        // ---------------------------------------------------------------
        // 2) CRIAR UM Date
        //    Date guarda UM MOMENTO: milissegundos desde 1970-01-01T00:00:00Z (a "época").
        // ---------------------------------------------------------------
        Date x1 = new Date();                                       // agora
        Date x2 = new Date(System.currentTimeMillis());             // agora (mesmo resultado que x1)
        Date x3 = new Date(0L);                                     // a própria época: 01/01/1970 00:00:00 GMT
        Date x4 = new Date(1000L * 60L * 60L * 5L);                 // 5 horas depois da época (o "L" evita estouro de int)

        // ---------------------------------------------------------------
        // 3) TEXTO -> Date (parse)
        //    O texto é lido no fuso da máquina, porque sdf1 e sdf2 não têm fuso definido.
        // ---------------------------------------------------------------
        Date y1 = sdf1.parse("25/06/2018");                         // sem hora: assume 00:00:00
        Date y2 = sdf2.parse("25/06/2018 15:42:07");

        // Instant (java.time) -> Date: ponte entre a API nova e a antiga.
        // "Z" = UTC. Para voltar: date.toInstant()
        Date y3 = Date.from(Instant.parse("2018-06-25T15:42:07Z"));

        // ---------------------------------------------------------------
        // 4) IMPRIMIR O Date DIRETO (toString)
        //    Sai em formato fixo e feio ("Mon Jun 25 ... 2018"), no fuso da máquina.
        //    x1 e x2 mudam a cada execução (dependem do momento atual).
        // ---------------------------------------------------------------
        System.out.println("x1: " + x1);
        System.out.println("x2: " + x2);
        System.out.println("x3: " + x3);
        System.out.println("x4: " + x4);
        System.out.println("y1: " + y1);
        System.out.println("y2: " + y2);
        System.out.println("y3: " + y3);
        System.out.println("-------------");

        // ---------------------------------------------------------------
        // 5) Date -> TEXTO com sdf2 (fuso da máquina)
        //    Exemplo rodando no Brasil (UTC-3):
        //    x3: 31/12/1969 21:00:00   (a época, 3 horas antes: ainda é o dia anterior)
        //    x4: 01/01/1970 02:00:00
        //    y1: 25/06/2018 00:00:00
        //    y2: 25/06/2018 15:42:07   (o mesmo texto que foi lido)
        //    y3: 25/06/2018 12:42:07   (15:42:07Z - 3h)
        // ---------------------------------------------------------------
        System.out.println("x1: " + sdf2.format(x1));
        System.out.println("x2: " + sdf2.format(x2));
        System.out.println("x3: " + sdf2.format(x3));
        System.out.println("x4: " + sdf2.format(x4));
        System.out.println("y1: " + sdf2.format(y1));
        System.out.println("y2: " + sdf2.format(y2));
        System.out.println("y3: " + sdf2.format(y3));
        System.out.println("-------------");

        // ---------------------------------------------------------------
        // 6) Date -> TEXTO com sdf3 (fixo em GMT)
        //    Os MESMOS objetos, com horas diferentes: o momento é o mesmo, só muda o relógio.
        //    x3: 01/01/1970 00:00:00   (a época em GMT)
        //    x4: 01/01/1970 05:00:00
        //    y1: 25/06/2018 03:00:00   (00:00 do Brasil + 3h)
        //    y2: 25/06/2018 18:42:07   (15:42:07 do Brasil + 3h)
        //    y3: 25/06/2018 15:42:07   (o Instant original, em UTC)
        // ---------------------------------------------------------------
        System.out.println("x1: " + sdf3.format(x1));
        System.out.println("x2: " + sdf3.format(x2));
        System.out.println("x3: " + sdf3.format(x3));
        System.out.println("x4: " + sdf3.format(x4));
        System.out.println("y1: " + sdf3.format(y1));
        System.out.println("y2: " + sdf3.format(y2));
        System.out.println("y3: " + sdf3.format(y3));

        // ---------------------------------------------------------------
        // PONTOS DE ATENÇÃO
        //  - Date é mutável e SimpleDateFormat não é thread-safe: motivos pelos quais a API foi substituída.
        //  - SimpleDateFormat é "tolerante" por padrão: "32/13/2018" pode ser aceito e virar outra data.
        //    Para recusar datas inválidas: sdf.setLenient(false).
        //  - O fuso muda o texto, nunca o momento.
        // ---------------------------------------------------------------
    }
}
