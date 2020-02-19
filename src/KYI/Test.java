package KYI;


import KYI.Entits.Shift;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import static KYI.Controllers.Controller.order;

public class Test {
    public static void main(String[] args) {

        System.out.println(Date.valueOf(LocalDate.now().minusYears(5)));
    }
}
