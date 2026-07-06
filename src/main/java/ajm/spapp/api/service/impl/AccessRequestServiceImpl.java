package ajm.spapp.api.service.impl;

import ajm.spapp.api.model.AccessRequest;
import ajm.spapp.api.model.RequestStatus;
import ajm.spapp.api.model.User;
import ajm.spapp.api.repository.AccessRequestRepository;
import ajm.spapp.api.repository.UserRepository;
import ajm.spapp.api.service.AccessRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccessRequestServiceImpl implements AccessRequestService {
    private final  AccessRequestRepository accessRequestRepository;
    private final UserRepository userRepository;

    public AccessRequestServiceImpl(AccessRequestRepository accessRequestRepository, UserRepository userRepository) {
        this.accessRequestRepository = accessRequestRepository;
        this.userRepository = userRepository;

    }

    @Override
    public AccessRequest createRequest(AccessRequest request) {
        request.setStatus(RequestStatus.CREATED);
        return accessRequestRepository.save(request);
    }

    @Override
    public List<AccessRequest> getAllRequests() {
        return accessRequestRepository.findAll();
    }

    @Override
    public List<AccessRequest> getByEmployeeId(Long employeeId) {
        return accessRequestRepository.findByEmployee_Id(employeeId);
    }

    @Override
    public List<AccessRequest> getByManagerId(Long managerId) {
        return accessRequestRepository.findByManager_Id(managerId);
    }

    @Override
    public AccessRequest getByStatus(RequestStatus status) {
        return (AccessRequest) accessRequestRepository.findByStatus(status);
    }

    @Override
    public User approveRequest(Long requestId, Long managerId) {
        User request = userRepository.findById(requestId)
                .orElseThrow();

        User manager = userRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        request.setStatus(RequestStatus.APPROVED);
        request.setManager(manager);
        request.setDecisionDate(LocalDateTime.now());

        return userRepository.save(request);
    }

    @Override
    public AccessRequest rejectedRequest(Long requestId, Long managerId, String reason) {

        AccessRequest request = accessRequestRepository.findById(requestId)
                .orElseThrow();

        Optional<User> manager = userRepository.findById(managerId);

        request.setStatus(RequestStatus.REJECTED);
        request.setManager(manager);
        request.setDecisionDate(LocalDateTime.now());
        request.setRejectionReason(reason);

        return  accessRequestRepository.save(request);

    }
}
