package repository.custom;

import Entity.OrderEntity;
import repository.CrudRepository;

import java.sql.SQLException;

public interface OrderRepository extends CrudRepository<OrderEntity,String> {
    boolean add(OrderEntity o1) throws SQLException;
}
