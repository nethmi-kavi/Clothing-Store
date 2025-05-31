package service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.User;
import service.custom.ProductService;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public Boolean addProduct(Product p1) throws SQLException {
        String query = "INSERT INTO product(id,name, price, qty) VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(query,p1.getId(),p1.getName(), p1.getPrice(), p1.getQuentity());
    }

    @Override
    public ObservableList getAll() throws SQLException {
        List<Product> productList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM product");
        while (resultSet.next())
           productList.add(new Product(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                     resultSet.getInt(4)
            ));
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        productList.forEach(product -> {
            productObservableList.add(product);
        });
        return productObservableList;

    }
}
