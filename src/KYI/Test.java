package KYI;


import java.sql.Date;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {

        System.out.println(Date.valueOf(LocalDate.now().minusYears(5)));
    }
}
