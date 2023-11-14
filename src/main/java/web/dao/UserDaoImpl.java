package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        User updatedUser = getUserById(id);
        if (updatedUser == null) {
            throw new IllegalArgumentException();
        }
        entityManager.remove(getUserById(id));
    }

    @Override
    public void updateUser(long id, User user) {
        User updatedUser = getUserById(id);
        updatedUser.setName(user.getName());
        updatedUser.setSurName(user.getSurName());
        entityManager.merge(updatedUser);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
}
