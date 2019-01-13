package client;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import model.Seat;

//        AVAILABLE = 0,
//        UNAVAILABLE = 1,
//        SELECTED = 2,
//        EMPTY = 3,
//        LOADING = 4,

public class SeatButton extends ToggleButton {
    private Seat seat;
    private int originalStatus;
    private int status;
    private int xPosition;
    private int yPosition;

    public SeatButton(int xPosition, int yPosition) {
        super();
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.originalStatus = 4;
        setTooltip(new Tooltip());
        setStatus(originalStatus);
        setPrefSize(35, 35);
        selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (status == 1) setSelected(false);
                else setStatus(2);
            }
            else setStatus(originalStatus);
        });
    }

    public SeatButton(Seat s) {
        this(s.getNumber(), s.getRow());
        this.seat = s;
//        setStatus();
    }

    public int getStatus() {
        return status;
    }

    public int getOriginalStatus() {
        return originalStatus;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setStatus(int status) {
        this.status = status;
        if (status == 0) {
            setStyle("-fx-background-color: lightgreen;");
            setTooltipText("To miejsce jest dostępne");
            setDisabled(false);
        }
        if (status == 1) {
            setStyle("-fx-background-color: indianred;");
            setTooltipText("Miejsce NIE dostępne");
            setDisabled(true);
        }
        if (status == 2) {
            setStyle("-fx-background-color: lightblue;");
            setTooltipText("Wybrałeś to miejsce");
        }
        if (status == 4) {
            setStyle("-fx-background-color: grey;");
            setTooltipText("Ladowanie informacji");
        }
    }


    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    private void setTooltipText(String tooltipText) {
        this.getTooltip().setText(tooltipText);
    }

    public Seat getSeat() {
        return seat;
    }

//    public enum SeatStatus {
//        AVAILABLE = 0,
//        UNAVAILABLE = 1,
//        SELECTED = 2,
//        EMPTY = 3,
//        LOADING = 4,
//    }
}
