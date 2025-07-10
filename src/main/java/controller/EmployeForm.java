package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.Product;
import model.User;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.ProductService;
import service.custom.UserService;
import util.CrudUtil;
import util.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeForm {

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colMob;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPass;

    @FXML
    private TableColumn colUser;

    @FXML
    private TableView tblUser;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtUsername;

    EmployeeService cs = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    public void initialize(){

        loadTable();

    }

    @FXML
    void btnAdd(ActionEvent event) throws SQLException {
        try {
            UserService us = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String mobile = txtMobile.getText().trim();
            String username = txtUsername.getText().trim();
            String password = txtPass.getText();




                User u1 = new User(id, name, mobile, username, password);
                boolean success = us.addUser(u1);

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("User registered successfully!");
                    alert.showAndWait();


                    cs.deleteEmployee(id);

                    loadTable();


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

    @FXML
    void btnSearch(ActionEvent event) throws SQLException {
        String id=txtId.getText();

        Employee e1=cs.searchById(id);
        if(e1==null){
            System.out.println("Wrong id");
        }
        txtName.setText(e1.getName());
        txtMobile.setText(e1.getMobild());
        txtUsername.setText(e1.getUsername());
        txtPass.setText(e1.getPassword());
    }

    public void loadTable(){
        try {

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colMob.setCellValueFactory(new PropertyValueFactory<>("mobild"));
            colUser.setCellValueFactory(new PropertyValueFactory<>("username"));
            colPass.setCellValueFactory(new PropertyValueFactory<>("password"));

            ObservableList<Employee> employees = cs.getAll();
            tblUser.setItems(employees);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
