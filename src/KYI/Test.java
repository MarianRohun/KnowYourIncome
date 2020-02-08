package KYI;


import KYI.Entits.Shift;

import java.time.LocalDate;
import java.time.LocalTime;

import static KYI.Controllers.Controller.order;

public class Test {
    public static void main(String[] args) {

        System.out.println(LocalDate.ofYearDay(LocalDate.now().getYear(),1));
    }
}
