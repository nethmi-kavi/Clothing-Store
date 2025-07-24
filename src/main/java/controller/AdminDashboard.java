package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AdminDashboard{

    @FXML
    private Pane dboard;

    @FXML
    private HBox root;

    @FXML
    private AnchorPane side_anker;

    @FXML
    void btnEmployee(ActionEvent event) {

        try {
            Pane productPane = FXMLLoader.load(getClass().getResource("/view/employe_form.fxml"));
            dboard.getChildren().clear();
            dboard.getChildren().add(productPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnOrders(ActionEvent event) {

        try {
            Pane productPane = FXMLLoader.load(getClass().getResource("/view/order_Page.fxml"));
            dboard.getChildren().clear();
            dboard.getChildren().add(productPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnProduct(ActionEvent event) {
        try {
            Pane productPane = FXMLLoader.load(getClass().getResource("/view/Product.fxml"));
            dboard.getChildren().clear();
            dboard.getChildren().add(productPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnReports(ActionEvent event) {
        try {
            Pane productPane = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
            dboard.getChildren().clear();
            dboard.getChildren().add(productPane);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void btnUser(ActionEvent event) {
        try {
            Pane productPane = FXMLLoader.load(getClass().getResource("/view/forgetPassword.fxml"));
            dboard.getChildren().clear();
            dboard.getChildren().add(productPane);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
