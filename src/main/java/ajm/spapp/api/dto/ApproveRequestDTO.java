package ajm.spapp.api.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ApproveRequestDTO(
        @Future
        @NotNull
        LocalDate expirationDate
) {}
