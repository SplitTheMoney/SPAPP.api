package ajm.spapp.api.dto;

import jakarta.validation.constraints.*;

public record CreateRequestRequestDTO(
        Long folderId,
        @NotNull(message = "Must type a justification")
        String justification,
        @NotNull(message = "Must specify the access type")
        String accessType
) {}