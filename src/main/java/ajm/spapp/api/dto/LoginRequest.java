package ajm.spapp.api.dto;

public record LoginRequest(
        String email,
        String password
) {
}