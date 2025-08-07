package service.custom;

import javafx.collections.ObservableList;
import model.Supplier;
import service.SuperService;

import java.sql.SQLException;

public interface SupplierService extends SuperService {
    Boolean addSupplier(Supplier s1) throws SQLException;
    ObservableList getAll() throws SQLException;
    boolean deleteSupplier(String id) throws SQLException;
    Supplier searchById(String id) throws SQLException;

    boolean UpdateSupplier(Supplier s1) throws SQLException;
}
