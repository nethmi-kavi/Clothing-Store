package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierView {
    @FXML
    private Pane dboard;

    @FXML
    private HBox root;

    @FXML
    private AnchorPane side_anker;


    @FXML
    void AddSupplier(ActionEvent event) throws IOException {
        try {
            Pane productPane = FXMLLoader.load(getClass().getResource("/view/Supplier.fxml"));
            dboard.getChildren().clear();
            dboard.getChildren().add(productPane);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void CheckSupplier(ActionEvent event) throws IOException {
        try {
            Pane productPane = FXMLLoader.load(getClass().getResource("/view/AvailableSuppliers.fxml"));
            dboard.getChildren().clear();
            dboard.getChildren().add(productPane);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

