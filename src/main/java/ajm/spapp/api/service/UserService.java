package ajm.spapp.api.service;

import ajm.spapp.api.dto.NewUserRequestDTO;
import ajm.spapp.api.dto.UserResponseDTO;
import ajm.spapp.api.model.Role;
import ajm.spapp.api.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    UserResponseDTO createUser(NewUserRequestDTO dto);

    UserResponseDTO updateUser(Long id, NewUserRequestDTO dto);

    UserResponseDTO deleteUser(Long id);

    List<User> getUsersByRole(Role role);

}
