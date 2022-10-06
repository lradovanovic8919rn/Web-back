package rs.raf.demo.repositories.user;

import rs.raf.demo.entities.User;

import java.util.List;

public interface UserRepository {

    List<User> allUser();

    User addUser(User user);

    User updateUser(User user, String email);

    void changeStatus(String email);

    User findUser(String username);

    User specificUser(String email);
}
