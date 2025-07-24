package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import service.ServiceFactory;
import service.custom.UserService;
import util.ServiceType;

import java.io.IOException;
import java.sql.SQLException;

public class PasswordChange {


    @FXML
    private TextField txtPword;

    @FXML
    private TextField txtId;



    UserService us = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    @FXML
    void cancelBtn(ActionEvent event) {
        txtId.clear();
        txtPword.clear();


    }



    @FXML
    void updateBtn(ActionEvent event) {
        try{
            String id = txtId.getText();
            String password = txtPword.getText();

            boolean success = us.UpdateUserPassword(id, password);


            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Password Updated successfully!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not Update Password");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }



    }

}
