package ajm.spapp.api.dto;

public record SharedFolderResponseDTO(
        Long id,
        String name,
        String path,
        String description
)
{ }
