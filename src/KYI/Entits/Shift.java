package KYI.Entits;




import java.time.LocalTime;

public class Shift {
    private String surnameOfEmployee;
    private LocalTime shiftStart;
    private LocalTime shiftEnd;

    public Shift(String surnameOfEmployee, LocalTime shiftStart, LocalTime shiftEnd) {
        this.surnameOfEmployee = surnameOfEmployee;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    public String getSurnameOfEmployee() {
        return surnameOfEmployee;
    }

    public void setSurnameOfEmployee(String surnameOfEmployee) {
        this.surnameOfEmployee = surnameOfEmployee;
    }

    public LocalTime getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(LocalTime shiftStart) {
        this.shiftStart = shiftStart;
    }

    public LocalTime getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(LocalTime shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "surnameOfEmployee='" + surnameOfEmployee + '\'' +
                ", shiftStart=" + shiftStart +
                ", shiftEnd=" + shiftEnd +
                '}';
    }
}



