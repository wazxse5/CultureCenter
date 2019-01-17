package client.controller;

import client.SeatButton;
import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import message.ReservationRequestMessage;

import java.util.ArrayList;
import java.util.List;

public class ChoosingSeatViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private GridPane gridPane;
    @FXML private Button reserveButton;
    @FXML private Button buyButton;
    @FXML private Label infoLabel;
    private List<List<SeatButton>> seatButtons;
    private int idEvent;
    private String price = "12.99";

    public void initialize() {
        reserveButton.setOnAction(event -> confirm("Zarezerwowany"));
        buyButton.setOnAction(event -> confirm("Zakupiony"));
    }

    public void confirm(String condition) {
        List<Integer> selected = new ArrayList<>();
        for (List<SeatButton> list : seatButtons) {
            for (SeatButton seatButton : list) {
                if (seatButton.isSelected()) {
                    selected.add(Integer.valueOf(seatButton.getText()));
                }
            }
        }
        if (!selected.isEmpty()) {
            ReservationRequestMessage message = new ReservationRequestMessage(idEvent, threadClient.getUserID(), price, "Normalny", condition, selected);
            threadClient.sendReservationRequest(message);
            float cost = Float.parseFloat(price) * selected.size();
            infoLabel.setText("Rezerwacja wysłana. Koszt: " + cost);
        }
    }

    public void setLayout(int idEvent, int width, int height) {
        this.idEvent = idEvent;
        gridPane.getChildren().clear();
        seatButtons = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            List<SeatButton> list = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                SeatButton seatButton = new SeatButton(i, j);
                list.add(seatButton);
            }
            seatButtons.add(list);
        }

        for (int i = 0; i < height; i++) {
            Label label = new Label("Rząd " + i);
            label.setPrefSize(70, 30);
            gridPane.add(label, 0, i);
        }
        for (int j = 0; j < seatButtons.size(); j++) {
            for (int i = 0; i < seatButtons.get(j).size(); i++) {
                gridPane.add(seatButtons.get(j).get(i), j + 1, i);
                seatButtons.get(j).get(i).setText(i*width+j + "");
            }
        }
    }

    public void refreshSeats(List<Integer> seats) {
        for (List<SeatButton> list : seatButtons) {
            for (SeatButton sb : list) {
                if (seats.contains(Integer.parseInt(sb.getText()))) {
                    sb.setStatus(1);
                }
                else sb.setStatus(0);
            }
        }
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

}
