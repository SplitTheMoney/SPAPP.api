package ajm.spapp.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @Email(message = "Email must be valid")
        String email,
        @NotBlank
        String password
) {
}