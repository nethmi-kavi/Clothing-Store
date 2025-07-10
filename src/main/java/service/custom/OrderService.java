package service.custom;

import model.Order;
import model.Product;
import service.SuperService;

import java.sql.SQLException;

public interface OrderService extends SuperService {
    Boolean placeOrder(Order o1) throws SQLException;
}
