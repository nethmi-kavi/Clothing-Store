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
import model.Employee;
import model.Supplier;
import model.User;
import service.ServiceFactory;
import service.custom.CompanySupplierService;
import service.custom.SupplierService;
import service.custom.UserService;
import util.ServiceType;

import java.sql.SQLException;

public class AddSuppliers {

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
    private TableView tblSupplier;

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


    SupplierService supply = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);


    public void initialize(){

        loadTable();

    }

    @FXML
    void btnAdd(ActionEvent event) {
        try {
           CompanySupplierService cs = ServiceFactory.getInstance().getServiceType(ServiceType.COMPANYSUPPLIER);

            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String regNo = txtReg.getText().trim();
            String mobile = txtMobile.getText().trim();
            String address = txtAddress.getText();
            String email=txtEmail.getText();




            CompanySupplier c1 = new CompanySupplier(id, name, regNo, mobile, address,email);
            boolean success = cs.addCompanySupplier(c1);

            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("CompanySupplier Added successfully!");
                alert.showAndWait();


                supply.deleteSupplier(id);

                loadTable();


            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not added company supplier");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }




    }

    @FXML
    void btnSearch(ActionEvent event) throws SQLException {
        String id=txtId.getText();

       Supplier s1=supply.searchById(id);
        if(s1==null){
            System.out.println("Wrong id");
        }
        txtName.setText(s1.getName());
        txtReg.setText(s1.getRegNo());
        txtMobile.setText(s1.getMobile());
        txtAddress.setText(s1.getAddress());
        txtEmail.setText(s1.getEmail());

    }

    public void loadTable(){
        try {

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colReg.setCellValueFactory(new PropertyValueFactory<>("regNo"));
            colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            ObservableList<Supplier> suppliers = supply.getAll();
            tblSupplier.setItems(suppliers);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
