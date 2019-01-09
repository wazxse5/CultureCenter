package client.controller;

import client.SeatButton;
import client.ThreadClient;
import client.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class ChoosingSeatViewController {
    private ViewManager viewManager;
    private ThreadClient threadClient;

    @FXML private GridPane gridPane;
    @FXML private Button confirmButton;
    private List<List<SeatButton>> seatButtons;

    public void initialize() {

    }

    public void confirm() {
        List<Integer> xPos = new ArrayList<>();
        List<Integer> yPos = new ArrayList<>();

        for (List<SeatButton> list : seatButtons) {
            for (SeatButton seatButton : list) {
                if (seatButton.isSelected()) {
                    xPos.add(seatButton.getxPosition());
                    yPos.add(seatButton.getyPosition());
                }
            }
        }
        // TODO: tu będzie wysłanie wiadomości reservationrequest do serwera
    }


    /**
     * Każdy integer oznacza status jednego pola
     * 0 = available
     * 1 = unavailable
     * 2 = empty
     * @param seats
     */
    public void setLayout(List<List<Integer>> seats) {
        int width = seats.size();
        int height = seats.get(0).size();

        gridPane.getChildren().clear();
        seatButtons = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            List<SeatButton> list = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                SeatButton seatButton = new SeatButton(i, j);
                seatButton.setStatus(seats.get(i).get(j));
                list.add(seatButton);
            }
            seatButtons.add(list);
        }

        for (int i = 0; i < height; i++) {
            Label label = new Label("Rząd " + i);
            label.setPrefSize(50, 30);
            gridPane.add(label, 0, i);
        }
        for (int i = 0; i < seatButtons.size(); i++) {
            for (int j = 0; j < seatButtons.get(i).size(); j++) {
                gridPane.add(seatButtons.get(i).get(j), i + 1, j);
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
