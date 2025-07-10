package controller;

import db.DBConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.ServiceFactory;
import service.custom.ProductService;
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
        txtName.clear();
        txtQuentity.clear();
        txtPrice.clear();


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

   boolean delete=ps.deleteProduct(id);
if(delete==true){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText("Product Deleted successfully!");
    alert.showAndWait();
    loadTable();

}else{
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Could not Delete Product");
    alert.showAndWait();

}



    }



    public void serchBtn(ActionEvent actionEvent) throws SQLException {

        String id=txtId.getText();
        Product p1 = ps.searchById(id);

        if(p1!=null){

            txtName.setText(p1.getName());
            txtPrice.setText(String.valueOf(p1.getPrice()));
            txtQuentity.setText(String.valueOf(p1.getPrice()));
        }
        else{


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(" Product not found.");
            alert.showAndWait();
        }
    }
    @FXML
    void updateBtn(ActionEvent event) {

        try{
            String id= txtId.getText();
            String name=txtName.getText();
            double price= Double.parseDouble(txtPrice.getText());
            int qty= Integer.parseInt(txtQuentity.getText());

            Product product=new Product(id,name,price,qty);
            boolean success= ps.update(product);

            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Product Updated successfully!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not Update Product");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        loadTable();

    }

    @FXML
    void reportBtn(ActionEvent event) throws JRException {
        try {
           JasperDesign design= JRXmlLoader.load("src/main/resources/report/product_report.jrxml");
           JasperReport jasperReport=JasperCompileManager.compileReport(design);
           JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
           JasperExportManager.exportReportToPdfFile(jasperPrint,"product_report.pdf");
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
