package ajm.spapp.api.service.impl;

import ajm.spapp.api.dto.AccessRequestResponseDTO;
import ajm.spapp.api.dto.ApproveRequestDTO;
import ajm.spapp.api.dto.CreateRequestRequestDTO;
import ajm.spapp.api.dto.RejectRequestDTO;
import ajm.spapp.api.mapper.AccessRequestMapper;
import ajm.spapp.api.model.*;
import ajm.spapp.api.repository.AccessRequestRepository;
import ajm.spapp.api.repository.SharedFolderRepository;
import ajm.spapp.api.repository.UserRepository;
import ajm.spapp.api.service.AccessRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccessRequestServiceImpl implements AccessRequestService {
    private final  AccessRequestRepository accessRequestRepository;
    private final UserRepository userRepository;
    private final SharedFolderRepository sharedFolderRepository;
    private final AccessRequestMapper accessRequestMapper;

    public AccessRequestServiceImpl(AccessRequestRepository accessRequestRepository, UserRepository userRepository, AccessRequestMapper accessRequestMapper, SharedFolderRepository sharedFolderRepository) {
        this.accessRequestRepository = accessRequestRepository;
        this.userRepository = userRepository;
        this.sharedFolderRepository = sharedFolderRepository;
        this.accessRequestMapper = accessRequestMapper;

    }

    @Override
    public AccessRequestResponseDTO createRequest(CreateRequestRequestDTO dto, String userEmail) {

        SharedFolder folder = sharedFolderRepository.findById(dto.folderId())
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        User employee = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        AccessRequest request = accessRequestMapper.toEntity(dto, employee, folder);
        accessRequestRepository.save(request);
        return accessRequestMapper.toDto(request);
    }

    @Override
    public List<AccessRequestResponseDTO> getAllRequests(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<AccessRequest> requests;
        if (user.getRole() == Role.EMPLOYEE) requests = accessRequestRepository.findByEmployee(user);
        else requests = accessRequestRepository.findAll();

        return accessRequestMapper.toDtoList(requests);
    }

    @Override
    public List<AccessRequestResponseDTO> getByEmployeeId(Long employeeId) {
        return accessRequestMapper.toDtoList(accessRequestRepository.findByEmployee_Id(employeeId));
    }

    @Override
    public List<AccessRequestResponseDTO> getByManagerId(Long managerId) {
        return accessRequestMapper.toDtoList(accessRequestRepository.findByManager_Id(managerId));
    }

    @Override
    public List<AccessRequestResponseDTO> getByStatus(RequestStatus status) {
        return accessRequestMapper.toDtoList(accessRequestRepository.findByStatus(status));
    }

    @Override
    public AccessRequestResponseDTO approveRequest(Long requestId, ApproveRequestDTO dto, String userEmail) {

        AccessRequest request = accessRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != RequestStatus.CREATED)   throw new RuntimeException("Request has already been answered");

        User manager = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        request.setStatus(RequestStatus.APPROVED);
        request.setManager(manager);
        request.setDecisionDate(LocalDateTime.now());
        request.setExpirationDate(dto.expirationDate());

        accessRequestRepository.save(request);
        return accessRequestMapper.toDto(request);
    }

    @Override
    public AccessRequestResponseDTO rejectRequest(Long requestId, RejectRequestDTO dto, String userEmail) {

        AccessRequest request = accessRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != RequestStatus.CREATED)   throw new RuntimeException("Request has already been answered");

        User manager = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        request.setStatus(RequestStatus.REJECTED);
        request.setManager(manager);
        request.setDecisionDate(LocalDateTime.now());
        request.setRejectionReason(dto.rejectionReason());

        accessRequestRepository.save(request);

        return accessRequestMapper.toDto(request);

    }
}
