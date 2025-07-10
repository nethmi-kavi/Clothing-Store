package repository.custom.impl;

import Entity.OrderDetailEntity;
import Entity.ProductEntity;
import model.Product;
import repository.custom.ProductRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository{

    @Override
    public boolean add(ProductEntity entity) throws SQLException {
        return CrudUtil.execute("INSERT INTO product VALUES(?,?,?,?)",
                entity.getId(),entity.getName(),entity.getPrice(),entity.getQuentity());
    }

    @Override
    public boolean updateQty(OrderDetailEntity entity) throws SQLException {
        String sql="UPDATE product SET qty = qty - ? WHERE id = ?";

        return CrudUtil.execute(sql,entity.getQty(),entity.getItemCode());
    }

    @Override
    public boolean update(ProductEntity entity) throws SQLException {
        return CrudUtil.execute("UPDATE product SET name = ?, price = ?, qty = ? WHERE id = ?",
                entity.getName(), entity.getPrice(), entity.getQuentity(), entity.getId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM product WHERE id=?",
              id  );
    }

    @Override
    public ProductEntity searchById(String id) throws SQLException {
        try {
            ResultSet rs=CrudUtil.execute("SELECT * FROM product WHERE id=?",id);
            if (rs.next()) {
                ProductEntity pe = new ProductEntity();
                pe.setId(rs.getString(1));
                pe.setName(rs.getString(2));
                pe.setPrice(rs.getDouble(3));
                pe.setQuentity(Integer.valueOf(rs.getString(4)));

                return pe;
             }
            else {
                return null;
            }

            } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public List<ProductEntity> getAll() {

        List<ProductEntity> list =new ArrayList<>();
        try{
            ResultSet rs=CrudUtil.execute("SELECT * FROM product");
            while (rs.next()){
                ProductEntity pe =new ProductEntity();
                pe.setId(rs.getString(1));
                pe.setName(rs.getString(2));
                pe.setPrice(rs.getDouble(3));
                pe.setQuentity(Integer.valueOf(rs.getString(4)));


                list.add(pe);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
