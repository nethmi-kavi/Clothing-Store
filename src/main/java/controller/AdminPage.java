package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPage {

    @FXML
    void btnEmployee(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employe_form.fxml"))));
        stage.show();
    }

    @FXML
    void btnProduct(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Product.fxml"))));
        stage.show();

    }

    @FXML
    void btnOrders(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/order_Page.fxml"))));
        stage.show();
    }

    @FXML
    void btnReports(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/reports.fxml"))));
        stage.show();

    }

    @FXML
    void btnUser(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserPage.fxml"))));
        stage.show();
    }

}
