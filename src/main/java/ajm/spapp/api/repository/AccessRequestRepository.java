package ajm.spapp.api.repository;

import ajm.spapp.api.model.AccessRequest;
import ajm.spapp.api.model.RequestStatus;
import ajm.spapp.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AccessRequestRepository extends JpaRepository<AccessRequest, Long> {
    List<AccessRequest> findByEmployee(User employee);

    List<AccessRequest> findByManager(User manager);

    List<AccessRequest> findByStatus(RequestStatus status);

    List<AccessRequest> findByEmployee_Id(Long employeeId);

    List<AccessRequest> findByManager_Id(Long managerId);
}
