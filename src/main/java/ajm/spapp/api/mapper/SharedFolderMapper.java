package ajm.spapp.api.mapper;

import ajm.spapp.api.dto.SharedFolderResponseDTO;
import ajm.spapp.api.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SharedFolderMapper {

    public SharedFolderResponseDTO toDto(SharedFolder folder) {
        return new SharedFolderResponseDTO(
                folder.getId(),
                folder.getName(),
                folder.getPath(),
                folder.getDescription()
        );
    }

    public List<SharedFolderResponseDTO> toDtoList(List<SharedFolder> folders) {
        return folders.stream().map(this::toDto).toList();
    }
}