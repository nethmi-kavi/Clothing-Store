package service.custom.impl;

import Entity.EmployeeEntity;
import Entity.OrderEntity;
import db.DBConnection;
import model.Order;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.OrderRepository;
import service.custom.OrderService;
import util.RepositoryType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
    OrderRepository or = DaoFactory.getInstance().getRepositoryType(RepositoryType.ORDER);

    @Override
    public Boolean placeOrder(Order o1) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            OrderEntity oe = new ModelMapper().map(o1, OrderEntity.class);

            boolean isOrderAdd = or.add(oe);
            if (isOrderAdd) {
                boolean isOrderDetail = new OrderDetailServiceImpl().addOrderDetail(o1.getOrderDetails());
                if (isOrderDetail) {
                    boolean isUpdateQty = new ProductServiceImpl().updateQty(o1.getOrderDetails());
                    if (isUpdateQty) {
                        con.commit();
                        System.out.println("Success game");
                        return true;
                    }
                }
            }
            con.rollback();
            return false;
        } catch (Exception e) {
            con.rollback();
            throw new RuntimeException(e);
        }finally {
            con.setAutoCommit(true);
        }

    }
}
