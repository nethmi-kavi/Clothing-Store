
    package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.User;
import service.ServiceFactory;
import service.SuperService;
import service.custom.UserService;
import service.custom.impl.UserServiceImpl;
import util.CrudUtil;
import util.ServiceType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;



    public class SignUp  {

        @FXML
        private TextField txtConfirm;

        @FXML
        private TextField txtMobile;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtPassword;

        @FXML
        private TextField txtUsername;

        UserService superService= ServiceFactory.getInstance().getServiceType(ServiceType.USER);

        @FXML

        void cancelBtn(ActionEvent event) {

        }

        @FXML
        void signUpBtn(ActionEvent event) {
            if (!txtPassword.getText().equals(txtConfirm.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Mismatch");
                alert.setHeaderText(null);
                alert.setContentText("Passwords do not match!");
                alert.showAndWait();
                return;
            }
            else {

                try {

                            String name=txtName.getText();
                            String mobile=txtMobile.getText();
                            String username=txtUsername.getText();
                            String password=txtPassword.getText();
                            String copassword=txtConfirm.getText();

                       User u1= new User(name,mobile,username,password,copassword);
                    boolean success=superService.addUser(u1);

                    if (success) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("User registered successfully!");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Could not register user");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

            }


        }

    }


