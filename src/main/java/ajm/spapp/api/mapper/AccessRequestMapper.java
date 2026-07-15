package ajm.spapp.api.mapper;

import ajm.spapp.api.dto.AccessRequestResponseDTO;
import ajm.spapp.api.dto.CreateRequestRequestDTO;
import ajm.spapp.api.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AccessRequestMapper {

    public AccessRequestResponseDTO toDto(AccessRequest request) {
        return new AccessRequestResponseDTO(
                request.getId(),
                request.getEmployee().getId(),
                request.getEmployee().getName(),
                request.getManager() != null ? request.getManager().getId() : null,
                request.getManager() != null ? request.getManager().getName() : null,
                request.getFolder().getId(),
                request.getFolder().getName(),
                request.getJustification(),
                request.getAccessType().name(),
                request.getStatus().name(),
                request.getRejectionReason(),
                request.getExpirationDate(),
                request.getCreatedAt(),
                request.getDecisionDate()
        );
    }

    public List<AccessRequestResponseDTO> toDtoList(List<AccessRequest> requests) {
        return requests.stream().map(this::toDto).toList();
    }

    public AccessRequest toEntity(
            CreateRequestRequestDTO dto,
            User employee,
            SharedFolder folder) {

        AccessRequest request = new AccessRequest();

        request.setEmployee(employee);
        request.setFolder(folder);
        request.setJustification(dto.justification());

        request.setAccessType(AccessType.valueOf(dto.accessType()));

        request.setStatus(RequestStatus.CREATED);

        request.setCreatedAt(LocalDateTime.now());

        return request;
    }
}