package service.custom;

import javafx.collections.ObservableList;
import model.OrderDetails;
import model.Product;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface ProductService extends SuperService {
    Boolean addProduct(Product p1) throws SQLException;
    ObservableList getAll() throws SQLException;
    Boolean deleteProduct(String id)throws SQLException;
    Boolean update (Product p1) throws SQLException;
    Product searchById (String id)throws SQLException;

    List<String> getProductIds() throws SQLException;

    boolean updateQty(List<OrderDetails> orderDetails) throws SQLException;
}
