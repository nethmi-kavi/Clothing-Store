package service.custom;

import javafx.collections.ObservableList;
import model.Product;
import model.User;
import service.SuperService;

import java.sql.SQLException;

public interface ProductService extends SuperService {
    Boolean addProduct(Product p1) throws SQLException;
    ObservableList getAll() throws SQLException;
}
