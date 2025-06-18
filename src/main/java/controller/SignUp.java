
    package controller;

import com.sun.javafx.charts.Legend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Employee;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.ServiceType;


    public class SignUp  {

        @FXML
        private TextField txtConfirm;

        @FXML
        private TextField txtId;

        @FXML
        private TextField txtMobile;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtPassword;

        @FXML
        private TextField txtUsername;

        EmployeeService superService= ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

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

                    String id=txtId.getText();
                            String name=txtName.getText();
                            String mobile=txtMobile.getText();
                            String username=txtUsername.getText();
                            String password=txtPassword.getText();
                            String copassword=txtConfirm.getText();

                       Employee u1= new Employee(id,name,mobile,username,password,copassword);
                    boolean success=superService.addEmployee(u1);

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


