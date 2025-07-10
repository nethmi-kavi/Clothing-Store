package repository.custom;

import Entity.OrderDetailEntity;
import Entity.ProductEntity;
import repository.CrudRepository;

import java.sql.SQLException;

public interface ProductRepository extends CrudRepository<ProductEntity, String> {
    boolean add(ProductEntity entity) throws SQLException;
    boolean updateQty(OrderDetailEntity entity) throws SQLException;
}
