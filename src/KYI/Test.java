package KYI;


import KYI.Entits.Shift;

import java.time.LocalTime;

public class Test {
    public static void main(String[] args) {
        Shift shift = new Shift ("Komar",LocalTime.parse("10:00"),LocalTime.parse("18:00"));

    }
}
