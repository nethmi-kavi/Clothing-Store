package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Employee;
import model.Supplier;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.SupplierService;
import util.CrudUtil;
import util.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierController {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRegNo;

    SupplierService superService= ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    public void initialize(){
        idGenerate();
    }

    @FXML
    void AddUpBtn(ActionEvent event) {



            try {

                String id=txtId.getText();
                String name=txtName.getText();
                String regNo=txtRegNo.getText();
                String mobile=txtMobile.getText();
                String address=txtAddress.getText();
                String mail=txtEmail.getText();

                if (mail.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {


                   Supplier u1 = new Supplier(id, name, regNo, mobile, address, mail);
                    boolean success = superService.addSupplier(u1);

                    if (success) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Supplier registered successfully!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Failed");
                        alert.setHeaderText("Registration Failed");
                        alert.setContentText("Could not add the supplier to the system.");
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
                alert.setHeaderText("Could not register Supplier");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }





    }

    @FXML
    void cancelBtn(ActionEvent event) {
        txtName.clear();
        txtEmail.clear();
        txtMobile.clear();
        txtRegNo.clear();
        txtAddress.clear();
    }

    public String idGenerate() {
        String prefix = "S";
        String newId = prefix + "001";

        try {
            ResultSet rs = CrudUtil.execute("SELECT id FROM supplier ORDER BY id DESC LIMIT 1");

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
