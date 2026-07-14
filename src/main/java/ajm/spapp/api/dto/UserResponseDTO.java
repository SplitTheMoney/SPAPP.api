package ajm.spapp.api.dto;

import ajm.spapp.api.model.Department;
import ajm.spapp.api.model.Role;

public record UserResponseDTO (
        Long id,
        String name,
        String email,
        Department department,
        Role role
) {}
