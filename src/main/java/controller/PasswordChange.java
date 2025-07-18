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
    private Label lblMobile;

    @FXML
    private Label lblName;

    @FXML
    private TextField txtPword;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtUsername;

    UserService us = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    @FXML
    void cancelBtn(ActionEvent event) {
        txtId.clear();
        txtPword.clear();
        txtUsername.clear();
        lblMobile.setText("");
        lblName.setText("");


    }

    @FXML
    void serchBtn(ActionEvent event) throws SQLException, IOException {
        String id=txtId.getText();
        String username=txtUsername.getText();



            User u1 = us.SearchById(id);

            if (u1 != null && username.equals(u1.getUsername())) {
                lblName.setText(u1.getName());
                lblMobile.setText(u1.getMobild());
                txtPword.setText(u1.getPassword());

            } else if (u1 != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter correct Username.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You can't Change Password. Please signup.");
                alert.showAndWait();
            }

    }

    @FXML
    void updateBtn(ActionEvent event) {
        try{
            User u1 =new User(txtId.getText(),lblName.getText(),lblMobile.getText(),txtUsername.getText(),txtPword.getText());
            boolean success=us.UpdateUser(u1);


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
