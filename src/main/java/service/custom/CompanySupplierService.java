package service.custom;

import javafx.collections.ObservableList;
import model.CompanySupplier;
import model.Employee;
import service.SuperService;

import java.sql.SQLException;

public interface CompanySupplierService extends SuperService {

    Boolean addCompanySupplier(CompanySupplier s1) throws SQLException;
    ObservableList getAll() throws SQLException;
    Boolean deleteCompanySupplier(String id)throws SQLException;
    CompanySupplier searchById(String id) throws SQLException;
    boolean UpdateCompanySupplier(CompanySupplier s1) throws SQLException;
}
