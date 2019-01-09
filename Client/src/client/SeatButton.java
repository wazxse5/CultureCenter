package client;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;

public class SeatButton extends ToggleButton {
    private SeatStatus originalStatus;
    private SeatStatus status;
    private int xPosition;
    private int yPosition;

    public SeatButton(int xPosition, int yPosition) {
        super("" + xPosition);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.originalStatus = SeatStatus.AVAILABLE;
        setTooltip(new Tooltip());
        setStatus(originalStatus);
        setPrefSize(30, 30);
        selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (status == SeatStatus.UNAVAILABLE) setSelected(false);
                else setStatus(SeatStatus.SELECTED);
            }
            else setStatus(originalStatus);
        });
    }

    public SeatStatus getStatus() {
        return status;
    }

    public SeatStatus getOriginalStatus() {
        return originalStatus;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
        if (status == SeatStatus.AVAILABLE) {
            setStyle("-fx-background-color: lightgreen;");
            setTooltipText("To miejsce jest dostępne");
        }
        if (status == SeatStatus.UNAVAILABLE) {
            setStyle("-fx-background-color: indianred;");
            setTooltipText("Miejsce NIE dostępne");
        }
        if (status == SeatStatus.SELECTED) {
            setStyle("-fx-background-color: lightblue;");
            setTooltipText("Wybrałeś to miejsce");
        }
    }

     public void setStatus(int statusInt) {
        if (statusInt == 0) setStatus(SeatStatus.AVAILABLE);
        if (statusInt == 1) setStatus(SeatStatus.UNAVAILABLE);
        if (statusInt == 2) setStatus(SeatStatus.EMPTY);
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

    public enum SeatStatus {
        AVAILABLE,
        UNAVAILABLE,
        EMPTY,
        SELECTED
    }
}
