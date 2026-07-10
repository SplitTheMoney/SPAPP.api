package ajm.spapp.api.dto;

import ajm.spapp.api.model.Role;

public record LoginResponseDTO(
        String token,
        Long id,
        String name,
        String email,
        Role role
) {
}