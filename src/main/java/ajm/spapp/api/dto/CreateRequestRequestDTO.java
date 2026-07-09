package ajm.spapp.api.dto;

import ajm.spapp.api.model.AccessType;

public record CreateRequestRequestDTO(
        Long folderId,
        String justification,
        AccessType accessType
) {}