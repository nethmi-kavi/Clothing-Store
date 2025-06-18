package service.custom;

import javafx.collections.ObservableList;
import model.Product;
import service.SuperService;

import java.sql.SQLException;

public interface ProductService extends SuperService {
    Boolean addProduct(Product p1) throws SQLException;
    ObservableList getAll() throws SQLException;
    Boolean deleteProduct(String id)throws SQLException;
}
