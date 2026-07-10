package ajm.spapp.api.controller;

import ajm.spapp.api.dto.AccessRequestResponseDTO;
import ajm.spapp.api.dto.ApproveRequestDTO;
import ajm.spapp.api.dto.CreateRequestRequestDTO;
import ajm.spapp.api.dto.RejectRequestDTO;
import ajm.spapp.api.model.*;
import ajm.spapp.api.service.AccessRequestService;
import ajm.spapp.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/requests")
public class AccessRequestController {
    private final AccessRequestService accessRequestService;
    private final UserService userService;

    public AccessRequestController(AccessRequestService accessRequestService, UserService userService) {
        this.accessRequestService = accessRequestService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<AccessRequestResponseDTO>> getAllRequests(Authentication auth) {
        return ResponseEntity.ok(accessRequestService.getAllRequests(auth.getName()));
    }

    @GetMapping("/employee")
    public ResponseEntity<List<AccessRequestResponseDTO>> getEmployeeRequests(Authentication auth) {
        Optional<User> currentUser = this.userService.getUserByEmail(auth.getName());

        return currentUser
                .map(user -> ResponseEntity.ok(
                        accessRequestService.getByEmployeeId(user.getId())
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/manager")
    public ResponseEntity<List<AccessRequestResponseDTO>> getManagerRequests(Authentication auth) {
        Optional<User> currentUser = this.userService.getUserByEmail(auth.getName());

        if (currentUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<AccessRequestResponseDTO> answeredRequests = this.accessRequestService.getByManagerId(currentUser.get().getId());
        List<AccessRequestResponseDTO> unAnsweredRequests = this.accessRequestService.getByStatus(RequestStatus.CREATED);

        List<AccessRequestResponseDTO> allRequests = Stream.concat(
                answeredRequests.stream(),
                unAnsweredRequests.stream()
        ).toList();

        return ResponseEntity.ok(allRequests);
    }

    @PostMapping("")
    public ResponseEntity<AccessRequestResponseDTO> createRequest(@RequestBody CreateRequestRequestDTO dto, Authentication auth) {
        return ResponseEntity.ok(accessRequestService.createRequest(dto, auth.getName()));
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')||hasRole('MANAGER')")
    public ResponseEntity<AccessRequestResponseDTO> approveRequest (@PathVariable Long id, @RequestBody ApproveRequestDTO dto, Authentication auth) {
        return ResponseEntity.ok(accessRequestService.approveRequest(id, dto, auth.getName()));
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')||hasRole('MANAGER')")
    public ResponseEntity<AccessRequestResponseDTO> rejectRequest (@PathVariable Long id, @RequestBody RejectRequestDTO dto, Authentication auth) {
        return ResponseEntity.ok(accessRequestService.rejectRequest(id, dto, auth.getName()));
    }
}
