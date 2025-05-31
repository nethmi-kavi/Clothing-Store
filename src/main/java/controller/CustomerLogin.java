package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML
    void loginBtn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminPage.fxml"))));
        stage.show();
    }

    @FXML
    void signUpBtn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"))));
        stage.show();

    }

}
