package controllers;
import algorithms.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Page[] pagesArr;
    int[] numbersArr;
    private static int fifoFaults;
    private static int lruFaults;
    private static int alruFaults;
    private static int optFaults;
    private static int randFaults;

    @FXML
    private TableView<Page> table;

    @FXML
    private TableColumn<Page, Integer> pagesCol;

    @FXML
    private TextField capacity;

    @FXML
    private TextField numberOfPages;

    @FXML
    private Button generatePagesButton;

    @FXML
    private Button calculateButton;

    @FXML
    private TextField range;

    @FXML
    void calculate(ActionEvent event) {
        int frames = Integer.valueOf(capacity.getText().strip());

        LRU.calculate(numbersArr, frames);
        lruFaults = LRU.getPage_faults();

        Optimal.calculate(numbersArr,frames);
        optFaults = Optimal.getPage_faults();

        ALRU.calculate(numbersArr,frames);
        alruFaults = ALRU.getPage_faults();

        FIFO.pageFaults(numbersArr,frames);
        fifoFaults = FIFO.getPage_faults();

        RAND.calculate(numbersArr,frames);
        randFaults = RAND.getPage_faults();

        changeScene(event);
    }

    @FXML
    void generatePages(ActionEvent event) {
        pagesArr = Page.generate(Integer.valueOf(numberOfPages.getText().strip()),Integer.valueOf(range.getText().strip()));
        numbersArr = new int[pagesArr.length];
        for(int i = 0; i<pagesArr.length ;i++) {
            numbersArr[i] = pagesArr[i].getNumber();
            table.getItems().add(pagesArr[i]);
        }
        calculateButton.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calculateButton.setVisible(false);
        pagesCol.setCellValueFactory(new PropertyValueFactory<>("number"));
    }

    public void changeScene(ActionEvent event){
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../assets/calculations.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String printFaults(){
        return "FIFO: " + fifoFaults
                + "\nLRU: " + lruFaults
                + "\nALRU: " + alruFaults
                + "\nOPT: " + optFaults
                + "\nRAND:" + randFaults;
    }

    public static int getLruFaults() {
        return lruFaults;
    }

    public static int getAlruFaults() {
        return alruFaults;
    }

    public static int getOptFaults() {
        return optFaults;
    }

    public static int getRandFaults() {
        return randFaults;
    }

    public static int getFifoFaults() {
        return fifoFaults;
    }
}
