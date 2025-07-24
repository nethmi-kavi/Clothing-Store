package controller;

import com.sun.javafx.collections.MappingChange;
import db.DBConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.ServiceFactory;
import service.custom.OrderService;
import service.custom.ProductService;
import service.custom.UserService;
import util.CrudUtil;
import util.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class OrderPage {

    @FXML
    private ComboBox btnCustomerId;

    @FXML
    private ComboBox btnItemCode;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblNet;

    @FXML
    private Label lblTime;

    @FXML
    private TextField lblOrderId;

    @FXML
    private TableView tblCarts;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtStock;

    public void initialize() throws SQLException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadDateAndTime();
        loadCustomerIds();
        loadProductIds();

        btnCustomerId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {

            try {
                setUsersValue((String) newVal);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        btnItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {

            setProductValue((String) newVal);
        });
        idGenerate();

    }


    @FXML
    private Label txtTotal;

    UserService us = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
    ProductService ps = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    OrderService os = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
    List<Cart> cartTM = new ArrayList<>();

    @FXML
    void btnCart(ActionEvent event) {
        String id = btnItemCode.getValue().toString();
        String desc = txtDesc.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtPrice.getText());
        double total = qty * unitPrice;

        cartTM.add(new Cart(id, desc, qty, unitPrice, total));

        tblCarts.setItems(FXCollections.observableArrayList(cartTM));

        getNetTotal();
    }

    private void getNetTotal() {
        double netTot = 0;
        for (Cart c1 : cartTM) {
            netTot += c1.getTotal();

            txtTotal.setText(String.valueOf(netTot));
        }

    }

    @FXML
    void btnPlaceOrder(ActionEvent event) throws SQLException {

        String orderId = lblOrderId.getText();
        String date = lblDate.getText();
        String userId = btnCustomerId.getValue().toString();

        ArrayList<OrderDetails> orderDetailsArrayList = new ArrayList<>();
        cartTM.forEach(cart -> orderDetailsArrayList.add(
                new OrderDetails(
                        orderId,
                        cart.getId(),
                        cart.getQty(),
                        cart.getUnitPrice())
        ));

        Order o1 = new Order(orderId, date, userId, orderDetailsArrayList);
        System.out.println(o1);

        os.placeOrder(o1);

    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        lblDate.setText(format1);

        System.out.println(LocalDate.now());

//        -------------------TIME--------------------

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime now = LocalTime.now();
                    lblTime.setText(now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }

    private void loadCustomerIds() throws SQLException {
        try {
            List<String> userIds = us.getUserIds();
            btnCustomerId.setItems(FXCollections.observableArrayList(userIds));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void loadProductIds() {
        try {
            List<String> productIds = ps.getProductIds();
            btnItemCode.setItems(FXCollections.observableArrayList(productIds));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setUsersValue(String id) throws SQLException {
        try {
            User u1 = us.SearchById(id);
            txtName.setText(u1.getName());
            txtMobile.setText(u1.getMobild());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setProductValue(String id) {
        try {
            Product p1 = ps.searchById(id);
            txtDesc.setText(p1.getName());
            txtStock.setText(String.valueOf(p1.getQuentity()));
            txtPrice.setText(String.valueOf(p1.getPrice()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String idGenerate() {
        String prefix = "O";
        String newId = prefix + "001";

        try {
            ResultSet rs = CrudUtil.execute("SELECT id FROM orders ORDER BY id DESC LIMIT 1");

            if (rs.next()) {
                String lastId = rs.getString("id"); // e.g., "O005"
                if (lastId != null && lastId.startsWith(prefix)) {
                    String numericPart = lastId.substring(prefix.length()); // "005"
                    int number = Integer.parseInt(numericPart); // 5
                    number++;
                    newId = String.format(prefix + "%03d", number); // O006
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        lblOrderId.setText(newId);
        return newId;
    }

    @FXML
    void btnPrintBill(ActionEvent event) {
        try {

            JasperDesign design = JRXmlLoader.load("src/main/resources/report/Bill3.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            String orderId = lblOrderId.getText();


            Map<String, Object> parameters = new HashMap<>();
            parameters.put("orderId", orderId);

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    DBConnection.getInstance().getConnection()
            );

            // 5. Export and view the report
            JasperExportManager.exportReportToPdfFile(jasperPrint, "produ_report.pdf");
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }



}






}
