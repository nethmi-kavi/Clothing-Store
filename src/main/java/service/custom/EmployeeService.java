package service.custom;

import javafx.collections.ObservableList;
import model.Employee;
import model.Product;
import service.SuperService;

import java.sql.SQLException;

public interface EmployeeService extends SuperService {
    Boolean addEmployee(Employee e1) throws SQLException;
    ObservableList getAll() throws SQLException;
    Boolean deleteEmployee(String id)throws SQLException;
    Employee searchById(String id) throws SQLException;
}
