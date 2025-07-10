package service.custom.impl;

import Entity.OrderDetailEntity;
import Entity.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetails;
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
        List<ProductEntity> productList = repository.getAll();
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();

        for (ProductEntity entity:productList){
            Product p1 =new ModelMapper().map(entity,Product.class);
            productObservableList.add(p1);
        }

     return productObservableList;
    }

    @Override
    public Boolean deleteProduct(String id) throws SQLException {

        return repository.delete(id);
    }

    @Override
    public Boolean update(Product p1) throws SQLException {
        ProductEntity pe= new ModelMapper().map(p1,ProductEntity.class);
        return repository.update(pe);
    }

    @Override
    public Product searchById(String id) throws SQLException {
        ProductEntity pe= repository.searchById(id);

        if(id==null) {
            return null;
        }
        if(pe==null){
            return null;
        }
        return new ModelMapper().map(pe,Product.class);
    }

    @Override
    public List<String> getProductIds() throws SQLException {
        List<Product>all = getAll();
        ArrayList<String> productIds =new ArrayList<>();
        all.forEach(p1->productIds.add(p1.getId()));

        return productIds;
    }

    @Override
    public boolean updateQty(List<OrderDetails> orderDetails) throws SQLException {
        for (OrderDetails detail : orderDetails){
            Boolean isUpdate=updateQty(detail);

            if(!isUpdate){
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(OrderDetails orderDetails) throws SQLException {
        OrderDetailEntity entity = new OrderDetailEntity();
        entity.setItemCode(orderDetails.getItemCode());
        entity.setQty(orderDetails.getQty());

        return repository.updateQty(entity);
    }
}
