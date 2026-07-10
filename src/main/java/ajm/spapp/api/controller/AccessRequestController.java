package ajm.spapp.api.controller;

import ajm.spapp.api.dto.AccessRequestResponseDTO;
import ajm.spapp.api.dto.ApproveRequestDTO;
import ajm.spapp.api.dto.CreateRequestRequestDTO;
import ajm.spapp.api.dto.RejectRequestDTO;
import ajm.spapp.api.model.*;
import ajm.spapp.api.service.AccessRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class AccessRequestController {
    private final AccessRequestService accessRequestService;

    public AccessRequestController(AccessRequestService accessRequestService) {
        this.accessRequestService = accessRequestService;
    }

    @GetMapping("")
    public ResponseEntity<List<AccessRequestResponseDTO>> getAllRequests(Authentication auth) {
        return ResponseEntity.ok(accessRequestService.getAllRequests(auth.getName()));
    }

    @PostMapping("")
    public ResponseEntity<AccessRequestResponseDTO> createRequest(@RequestBody CreateRequestRequestDTO dto, Authentication auth) {
        return ResponseEntity.ok(accessRequestService.createRequest(dto, auth.getName()));
    }
  
    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')||hasRole('MANAGER')")
    public ResponseEntity<List<AccessRequestResponseDTO>> getPendingRequests() {
        return ResponseEntity.ok(accessRequestService.getPendingRequests());
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
