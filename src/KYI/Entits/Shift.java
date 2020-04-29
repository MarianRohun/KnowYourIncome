package KYI.Entits;

public class Shift {
    private String worker;
    private Double layoutX;
    private Double layoutY;
    private String shiftColor;
    private String shiftTime;

    public Shift(String worker,Double layoutX, Double layoutY, String shiftColor, String shiftTime) {
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.shiftColor = shiftColor;
        this.shiftTime = shiftTime;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public Double getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(Double layoutX) {
        this.layoutX = layoutX;
    }

    public Double getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(Double layoutY) {
        this.layoutY = layoutY;
    }

    public String getShiftColor() {
        return shiftColor;
    }

    public void setShiftColor(String shiftColor) {
        this.shiftColor = shiftColor;
    }


}
