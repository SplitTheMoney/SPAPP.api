package ajm.spapp.api.dto;

import ajm.spapp.api.model.Department;
import ajm.spapp.api.model.Role;

public record NewUserRequestDTO (
    String name,
    String email,
    String password,
    Department department,
    Role role
) {}
