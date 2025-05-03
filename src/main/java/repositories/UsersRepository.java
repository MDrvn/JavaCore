package repositories;

import model.User;
import java.util.List;

public interface UsersRepository {
    void create(User user);
    User findById(String id);
    List<User> findAll();
    void update(User user);
    void deleteById(String id);
    void deleteAll();
    List<User> findByAge(Integer age);
    List<User> findByIsWorker(boolean isWorker);
}