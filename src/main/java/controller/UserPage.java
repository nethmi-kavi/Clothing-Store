package controller;

import Entity.UserEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import model.User;
import service.ServiceFactory;
import service.SuperService;
import service.custom.ProductService;
import service.custom.UserService;
import util.ServiceType;

import java.sql.SQLException;

public class UserPage {

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colMob;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colUser;

    @FXML
    private TableView tblUser;

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

    UserService us = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    public void initialize(){
        loadTable();
    }

    @FXML
    void cancelBtn(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtUsername.clear();
        txtPassword.clear();
        txtMobile.clear();


    }

    @FXML
    void deleteBtn(ActionEvent event) throws SQLException {
        String id = txtId.getText();
        boolean delete=us.deleteUser(id);
        if(delete==true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("User Deleted successfully!");
            alert.showAndWait();
            loadTable();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not Delete User");
            alert.showAndWait();

        }



    }

    @FXML
    void serchBtn(ActionEvent event) throws SQLException {

        User u1 =us.SearchById(txtId.getText());

        if(u1 != null){
            txtName.setText(u1.getName());
            txtMobile.setText(u1.getMobild());
            txtUsername.setText(u1.getUsername());
            txtPassword.setText(u1.getPassword());

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(" User not found.");
            alert.showAndWait();
        }

    }

    @FXML
    void updateBtn(ActionEvent event) throws SQLException {
       try{
        User u1 =new User(txtId.getText(),txtName.getText(),txtMobile.getText(),txtUsername.getText(),txtPassword.getText());
        boolean success=us.UpdateUser(u1);


        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("User Updated successfully!");
            alert.showAndWait();
        }
    } catch (Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not Update User");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    loadTable();


}

    public void loadTable(){
        try {



            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colMob.setCellValueFactory(new PropertyValueFactory<>("mobild" +
                    ""));
            colUser.setCellValueFactory(new PropertyValueFactory<>("username"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

            ObservableList<User> users=us.getAll();


            tblUser.setItems(users);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
