package client.controller;

import client.SeatButton;
import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import message.ReservationRequestMessage;
import model.Seat;

import java.util.ArrayList;
import java.util.List;

public class ChoosingSeatViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private GridPane gridPane;
    @FXML private Button reserveButton;
    @FXML private Button buyButton;
    private List<List<SeatButton>> seatButtons;
    private int idEvent;
    private String price = "12.99";

    public void initialize() {
        reserveButton.setOnAction(event -> confirm("Zarezerwowany"));
        buyButton.setOnAction(event -> confirm("Zakup"));
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

    public void refreshSeats(List<Seat> seats) {
//
//        gridPane.getChildren().clear();
//        seatButtons = new ArrayList<>();
//        for (int i = 0; i < width; i++) {
//            List<SeatButton> list = new ArrayList<>();
//            for (int j = 0; j < height; j++) {
//                SeatButton seatButton = new SeatButton(i, j);
//                seatButton.setStatus(seats.get(i).get(j));
//                list.add(seatButton);
//            }
//            seatButtons.add(list);
//        }

//        for (int i = 0; i < height; i++) {
//            Label label = new Label("Rząd " + i);
//            label.setPrefSize(50, 30);
//            gridPane.add(label, 0, i);
//        }
//        for (int i = 0; i < seatButtons.size(); i++) {
//            for (int j = 0; j < seatButtons.get(i).size(); j++) {
//                gridPane.add(seatButtons.get(i).get(j), i + 1, j);
//            }
//        }
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setThreadClient(ThreadClient threadClient) {
        this.threadClient = threadClient;
    }

}
