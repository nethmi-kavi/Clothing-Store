

    package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

    public class AdminSupplierView {



        @FXML
        void AddComSuppliers(ActionEvent event) throws IOException {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddSuppliers.fxml"))));
            stage.show();

        }

        @FXML
        void AddSupplier(ActionEvent event) throws IOException {

            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Supplier.fxml"))));
            stage.show();

        }

        @FXML
        void CheckSupplier(ActionEvent event) throws IOException {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AvailableSuppliers.fxml"))));
            stage.show();

        }



}
