package ajm.spapp.api.dto;

import ajm.spapp.api.model.AccessType;
import jakarta.validation.constraints.NotBlank;

public record CreateRequestRequestDTO(
        Long folderId,
        @NotBlank
        String justification,
        AccessType accessType
) {}