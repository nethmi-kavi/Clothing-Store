package service.custom.impl;

import Entity.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductRepository;
import service.custom.ProductService;
import util.CrudUtil;
import util.RepositoryType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductRepository repository= DaoFactory.getInstance().getRepositoryType(RepositoryType.PRODUCT);


    @Override
    public Boolean addProduct(Product p1) throws SQLException {
        ProductEntity pe= new ModelMapper().map(p1,ProductEntity.class);

      return repository.add(pe);

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

    @Override
    public Boolean deleteProduct(String id) throws SQLException {

        return repository.delete(id);
    }
}
