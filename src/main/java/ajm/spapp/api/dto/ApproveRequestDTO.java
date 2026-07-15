package ajm.spapp.api.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ApproveRequestDTO(
        @NotNull(message = "Expiration date is required")
        @Future(message = "Expiration date must be future")
        LocalDate expirationDate
) {}
