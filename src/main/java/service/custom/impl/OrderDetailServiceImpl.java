package service.custom.impl;

import Entity.OrderDetailEntity;
import Entity.OrderEntity;
import model.OrderDetails;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.OrderDetailRepository;
import util.CrudUtil;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailServiceImpl  {
    OrderDetailRepository odr= DaoFactory.getInstance().getRepositoryType(RepositoryType.ORDERDETAIL);
    public Boolean addOrderDetail(List <OrderDetails> orderDetails) throws SQLException {

        for (OrderDetails details: orderDetails){

           Boolean addDetail= addOrderDetail(details);

           if(!addDetail){
               return false;
           }
        }
        return true;
    }

    public Boolean addOrderDetail(OrderDetails orderDetails) throws SQLException {

        OrderDetailEntity ode = new ModelMapper().map(orderDetails, OrderDetailEntity.class);
       return odr.add(ode);
    }


}
