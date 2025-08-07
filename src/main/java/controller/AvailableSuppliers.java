package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CompanySupplier;
import model.Supplier;
import model.User;
import service.ServiceFactory;
import service.custom.CompanySupplierService;
import util.ServiceType;

import java.sql.SQLException;

public class AvailableSuppliers {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colEmail;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colMobile;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colReg;

    @FXML
    private TableView tblAvailableSupplier;

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
    private TextField txtReg;

    CompanySupplierService cs = ServiceFactory.getInstance().getServiceType(ServiceType.COMPANYSUPPLIER);
    public void initialize(){

        loadTable();

    }
    @FXML
    void cancelBtn(ActionEvent event) {

    }

    @FXML
    void deleteBtn(ActionEvent event) throws SQLException {
        String id = txtId.getText();
        boolean delete = cs.deleteCompanySupplier(id);
        if (delete == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Supplier Deleted successfully!");
            alert.showAndWait();
            loadTable();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not Delete Supplier");
            alert.showAndWait();

        }
    }


    @FXML
    void serchBtn(ActionEvent event) throws SQLException {


        CompanySupplier s1 = cs.searchById(txtId.getText());

        if (s1 != null) {
            txtName.setText(s1.getName());
            txtReg.setText(s1.getRegNo());
            txtMobile.setText(s1.getMobile());
            txtAddress.setText(s1.getAddress());
            txtEmail.setText(s1.getEmail());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(" Supplier not found.");
            alert.showAndWait();
        }

    }

    @FXML
    void updateBtn(ActionEvent event) {


        try {
            CompanySupplier s1 = new CompanySupplier(txtId.getText(), txtName.getText(), txtReg.getText(), txtMobile.getText(), txtAddress.getText(), txtEmail.getText());
            boolean success = cs.UpdateCompanySupplier(s1);


            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Supplier Updated successfully!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not Update Supplier");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        loadTable();


    }

    public void loadTable() {
        try {

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colReg.setCellValueFactory(new PropertyValueFactory<>("regNo"));
            colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            ObservableList<CompanySupplier> suppliers = cs.getAll();
            tblAvailableSupplier.setItems(suppliers);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
