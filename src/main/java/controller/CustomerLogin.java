package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.UserService;
import util.ServiceType;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerLogin {

    @FXML
    private AnchorPane background;

    @FXML
    private AnchorPane background11;

    @FXML
    private Label lbl11;

    @FXML
    private TextField txtPWord;

    @FXML
    private TextField txtUser;

    UserService us = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    @FXML
    void loginBtn(ActionEvent event) throws IOException, SQLException {
      success();
    }

    @FXML
    void signUpBtn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"))));
        stage.show();

    }
     public void success() throws IOException, SQLException {
        String user=txtUser.getText();
        String password=txtPWord.getText();

        if(user.equals("admin") && password.equals("1234")){
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));
            stage.show();
        }
        else {
            User u1 = us.SearchByUsername(user);

            if (u1 != null && password.equals(u1.getPassword())) {
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashboard.fxml"))));
                stage.show();
            } else if (u1 != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter correct password.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You can't login. Please signup.");
                alert.showAndWait();
            }
        }
     }

}
