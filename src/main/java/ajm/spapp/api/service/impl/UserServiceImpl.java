package ajm.spapp.api.service.impl;

import ajm.spapp.api.dto.NewUserRequestDTO;
import ajm.spapp.api.dto.UserResponseDTO;
import ajm.spapp.api.exception.ResourceNotFoundException;
import ajm.spapp.api.mapper.UserMapper;
import ajm.spapp.api.model.Role;
import ajm.spapp.api.model.User;
import ajm.spapp.api.repository.UserRepository;
import ajm.spapp.api.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return userMapper.toDtoList(users);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserResponseDTO createUser(NewUserRequestDTO dto) {
        User user = userMapper.toEntity(dto, passwordEncoder.encode(dto.password()));
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, NewUserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setName(dto.name());
        user.setEmail(dto.email());
        if (!dto.password().isEmpty()) {
            user.setPasswordHash(passwordEncoder.encode(dto.password()));
        }
        user.setDepartment(dto.department());
        user.setRole(dto.role());

        userRepository.save(user);

        return userMapper.toDto(user);
    }



    @Override
    public UserResponseDTO deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.deleteById(id);

        return userMapper.toDto(user);
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }
}
