package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(long id);
    void updateUser(long id, User user);
    User getUserById(long id);
}
