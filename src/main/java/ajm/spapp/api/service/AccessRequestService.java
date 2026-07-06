package ajm.spapp.api.service;

import ajm.spapp.api.model.AccessRequest;
import ajm.spapp.api.model.RequestStatus;
import ajm.spapp.api.model.User;

import java.util.List;

public interface AccessRequestService {

    AccessRequest createRequest(AccessRequest request);

    List<AccessRequest> getAllRequests();

    List<AccessRequest> getByEmployeeId(Long employeeId);

    List<AccessRequest> getByManagerId(Long managerId);

    AccessRequest getByStatus(RequestStatus status);

    User approveRequest(Long requestId, Long managerId);

    AccessRequest rejectedRequest(Long requestId, Long ManagerId, String reason);
}
