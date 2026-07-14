package ajm.spapp.api.mapper;

import ajm.spapp.api.dto.NewUserRequestDTO;
import ajm.spapp.api.dto.UserResponseDTO;
import ajm.spapp.api.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserResponseDTO toDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getDepartment(),
                user.getRole()
        );
    }

    public List<UserResponseDTO> toDtoList(List<User> users) {
        return users.stream().map(this::toDto).toList();
    }

    public User toEntity(NewUserRequestDTO dto, String hash) {
        User user = new User();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPasswordHash(hash);
        user.setDepartment(dto.department());
        user.setRole(dto.role());

        return user;
    }
}
