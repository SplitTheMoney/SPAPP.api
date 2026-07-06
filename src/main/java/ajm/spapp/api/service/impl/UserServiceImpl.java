package ajm.spapp.api.service.impl;

import ajm.spapp.api.model.User;
import ajm.spapp.api.repository.UserRepository;
import ajm.spapp.api.service.UserService;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public User updateUser(User user) {
        return null;
    }



    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return List.of();
    }
}
