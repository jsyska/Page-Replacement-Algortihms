package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Calculations implements Initializable {

    Controller ctrl = new Controller();

    @FXML
    private Button back;

    @FXML
    private BarChart<Integer, String> pagesChart;

    @FXML
    private Label faults;


    @FXML
    void goBack(ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../assets/main.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            //get the stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series set = new XYChart.Series<>();
        set.getData().add(new XYChart.Data("FIFO",ctrl.getFifoFaults()));
        set.getData().add(new XYChart.Data("LRU",ctrl.getLruFaults()));
        set.getData().add(new XYChart.Data("ALRU",ctrl.getAlruFaults()));
        set.getData().add(new XYChart.Data("OPT",ctrl.getOptFaults()));
        set.getData().add(new XYChart.Data("RAND",ctrl.getRandFaults()));
        pagesChart.getData().addAll(set);

        faults.setText(ctrl.printFaults());
    }
}
