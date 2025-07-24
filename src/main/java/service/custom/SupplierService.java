package service.custom;

import javafx.collections.ObservableList;
import model.Supplier;

import java.sql.SQLException;

public interface SupplierService {
    Boolean addSupplier(Supplier s1) throws SQLException;
    ObservableList getAll() throws SQLException;
    Boolean deleteSupplier(String id)throws SQLException;
    Supplier searchById(String id) throws SQLException;
}
