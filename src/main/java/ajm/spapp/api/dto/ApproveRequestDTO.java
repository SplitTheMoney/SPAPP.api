package ajm.spapp.api.dto;

import java.time.LocalDate;

public record ApproveRequestDTO(
        LocalDate expirationDate
) {}
