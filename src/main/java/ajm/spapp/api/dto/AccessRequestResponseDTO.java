package ajm.spapp.api.dto;

import ajm.spapp.api.model.AccessType;
import ajm.spapp.api.model.Department;
import ajm.spapp.api.model.RequestStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AccessRequestResponseDTO(
        Long id,

        Long employeeId,
        String employeeName,
        Department employeeDepartment,

        Long managerId,
        String managerName,

        Long folderId,
        String folderPath,

        String justification,

        AccessType accessType,

        RequestStatus status,

        String rejectionReason,

        LocalDate expirationDate,

        LocalDateTime createdAt,
        LocalDateTime decisionDate
) {}