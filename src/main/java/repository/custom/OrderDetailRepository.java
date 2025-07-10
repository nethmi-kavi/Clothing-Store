package repository.custom;

import Entity.OrderDetailEntity;
import Entity.OrderEntity;
import repository.SuperRepository;

import java.sql.SQLException;

public interface OrderDetailRepository extends SuperRepository {
    boolean add(OrderDetailEntity o1) throws SQLException;
}
