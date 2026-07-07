package ajm.spapp.api.service;

import ajm.spapp.api.model.Role;
import ajm.spapp.api.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);

    List<User> getUsersByRole(Role role);

}
