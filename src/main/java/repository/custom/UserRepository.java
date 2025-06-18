package repository.custom;

import Entity.UserEntity;
import model.User;
import repository.CrudRepository;

import java.sql.SQLException;

public interface UserRepository extends CrudRepository<UserEntity,String> {
 UserEntity searchByUsername (String username)throws  SQLException;
}
