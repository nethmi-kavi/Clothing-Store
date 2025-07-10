package repository.custom.impl;

import Entity.OrderDetailEntity;
import repository.custom.OrderDetailRepository;
import util.CrudUtil;

import java.sql.SQLException;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    @Override
    public boolean add(OrderDetailEntity o1) throws SQLException {
        return CrudUtil.execute("INSERT INTO orderdetail (orderId, itemCode, qty, unitPrice) VALUES (?, ?, ?, ?)",
                o1.getId(), o1.getItemCode(), o1.getQty(), o1.getUnitPrice());
    }


}
