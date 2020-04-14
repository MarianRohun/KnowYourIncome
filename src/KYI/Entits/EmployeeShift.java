package KYI.Entits;

import javafx.scene.control.Button;

import java.util.ArrayList;


public class EmployeeShift {
    private Button employeeSurname;
    private ArrayList<Button> shifts;


    public EmployeeShift(Button employeeSurname, ArrayList<Button> shifts) {
        this.employeeSurname = employeeSurname;
        this.shifts = shifts;
    }


    public Button getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(Button employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public ArrayList<Button> getShifts() {
        return shifts;
    }

    public void setShifts(ArrayList<Button> shifts) {
        this.shifts = shifts;
    }
}

