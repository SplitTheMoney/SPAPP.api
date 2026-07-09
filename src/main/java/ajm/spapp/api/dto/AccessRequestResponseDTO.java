package ajm.spapp.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AccessRequestResponseDTO(
        Long id,

        Long employeeId,
        String employeeName,

        Long managerId,
        String managerName,

        Long folderId,
        String folderName,

        String justification,

        String accessType,

        String status,

        String rejectionReason,

        LocalDate expirationDate,

        LocalDateTime createdAt,
        LocalDateTime decisionDate
) {}