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

    List<AccessRequest> getByStatus(RequestStatus status);

    AccessRequest approveRequest(Long requestId, Long managerId);

    AccessRequest rejectedRequest(Long requestId, Long ManagerId, String reason);
}
