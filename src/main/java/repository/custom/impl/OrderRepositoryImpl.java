package repository.custom.impl;

import Entity.OrderEntity;
import repository.custom.OrderRepository;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public boolean add(OrderEntity entity) throws SQLException {
        return CrudUtil.execute("INSERT INTO orders VALUES (?,?,?)",
                entity.getOrderId(),entity.getDate(),entity.getUserId());
    }

    @Override
    public boolean update(OrderEntity entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }

    @Override
    public OrderEntity searchById(String s) throws SQLException {
        return null;
    }

    @Override
    public List<OrderEntity> getAll() {
        return List.of();
    }
}
