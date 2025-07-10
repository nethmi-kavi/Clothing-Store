
    package controller;

import com.sun.javafx.charts.Legend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Employee;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.CrudUtil;
import util.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;


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

        public  void initialize(){
            idGenerate();
        }

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

                    if (username.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {


                        Employee u1 = new Employee(id, name, mobile, username, password, copassword);
                        boolean success = superService.addEmployee(u1);

                        if (success) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText(null);
                            alert.setContentText("User registered successfully!");
                            alert.showAndWait();
                        }
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Failed");
                        alert.setHeaderText("Registration Failed");
                        alert.setContentText("Could not add the user to the system.");
                        alert.showAndWait();
                    }
                      }
                    else {

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Invalid Email");
                            alert.setHeaderText("Email Validation Failed");
                            alert.setContentText("Please enter a valid Gmail address (e.g., user@gmail.com).");
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

        public String idGenerate() {
            String prefix = "E";
            String newId = prefix + "001";

            try {
                ResultSet rs = CrudUtil.execute("SELECT id FROM employee ORDER BY id DESC LIMIT 1");

                if (rs != null && rs.next()) {
                    String lastId = rs.getString("id");
                    System.out.println("Last ID from DB: " + lastId);

                    if (lastId != null && lastId.matches("^" + prefix + "\\d{3}$")) {
                        String numericPart = lastId.substring(prefix.length());
                        int number = Integer.parseInt(numericPart);
                        number++;
                        newId = prefix + String.format("%03d", number);
                    } else {
                        System.out.println("Last ID format invalid, using default.");
                    }
                } else {
                    System.out.println("No previous IDs found, using default.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            
            if (txtId != null) {
                txtId.setText(newId);
            } else {
                System.err.println("txtId is null!");
            }

            return newId;
        }


    }


