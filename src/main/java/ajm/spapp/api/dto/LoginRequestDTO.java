package ajm.spapp.api.dto;

public record LoginRequestDTO(
        String email,
        String password
) {
}