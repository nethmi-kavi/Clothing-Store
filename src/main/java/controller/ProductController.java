package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import service.ServiceFactory;
import service.custom.ProductService;
import service.custom.UserService;
import util.CrudUtil;
import util.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;




public class ProductController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuentity;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableView tblProduct;


    ProductService ps= ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    public void initialize() {
        idGenerate();
        loadTable();
    }

    @FXML
    void cancelBtn(ActionEvent event) {

    }

    @FXML
    void saveBtn(ActionEvent event) throws SQLException {
try{
        String id= txtId.getText();
        String name=txtName.getText();
        double price= Double.parseDouble(txtPrice.getText());
        int qty= Integer.parseInt(txtQuentity.getText());

        Product product=new Product(id,name,price,qty);
        boolean success= ps.addProduct(product);

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Product registered successfully!");
            alert.showAndWait();
        }
    } catch (Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not register Product");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

        loadTable();
}

public void loadTable(){
    try {

        ProductService cs = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quentity"));

        ObservableList <Product> products=ps.getAll();


        tblProduct.setItems(products);


    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    public String idGenerate() {
        String prefix = "P";
        String newId = prefix + "001";

        try {
            ResultSet rs = CrudUtil.execute("SELECT id FROM product ORDER BY id DESC LIMIT 1");

            if (rs.next()) {
                String lastId = rs.getString("id");
                String numericPart = lastId.substring(prefix.length());

                int number = Integer.parseInt(numericPart);
                number++;

                newId = String.format(prefix + "%03d", number);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        txtId.setText(newId);
        return newId;
    }

    @FXML
    void deleteBtn(ActionEvent event) throws SQLException {

    String id=txtId.getText();
   ps.deleteProduct(id);

   loadTable();

    }



    public void serchBtn(ActionEvent actionEvent) {
    }
}
