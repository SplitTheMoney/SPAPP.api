package ajm.spapp.api.service;

import ajm.spapp.api.dto.AccessRequestResponseDTO;
import ajm.spapp.api.dto.ApproveRequestDTO;
import ajm.spapp.api.dto.CreateRequestRequestDTO;
import ajm.spapp.api.dto.RejectRequestDTO;
import ajm.spapp.api.model.RequestStatus;

import java.util.List;

public interface AccessRequestService {

    AccessRequestResponseDTO createRequest(CreateRequestRequestDTO dto, String userEmail);

    List<AccessRequestResponseDTO> getAllRequests(String userEmail);

    List<AccessRequestResponseDTO> getByEmployeeId(Long employeeId);

    List<AccessRequestResponseDTO> getByManagerId(Long managerId);

    List<AccessRequestResponseDTO> getByStatus(RequestStatus status);

    AccessRequestResponseDTO approveRequest(Long requestId, ApproveRequestDTO dto, String userEmail);

    AccessRequestResponseDTO rejectRequest(Long requestId, RejectRequestDTO dto, String userEmail);
}
