package repository.custom;

import Entity.EmployeeEntity;
import repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity,String> {
}
