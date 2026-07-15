package ajm.spapp.api.controller;

import ajm.spapp.api.dto.SharedFolderResponseDTO;
import ajm.spapp.api.model.SharedFolder;
import ajm.spapp.api.model.User;
import ajm.spapp.api.service.SharedFolderService;
import ajm.spapp.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/folders")
public class SharedFolderController {
    private final SharedFolderService sharedFolderService;
    private final UserService userService;

    public SharedFolderController(SharedFolderService sharedFolderService, UserService userService) {
        this.sharedFolderService = sharedFolderService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<SharedFolderResponseDTO>> getAllFolders() {
        return ResponseEntity.ok(sharedFolderService.getAllFolders());
    }

    @GetMapping("/employee")
    public ResponseEntity<List<SharedFolder>> getEmployeeFolders(Authentication auth) {
        Optional<User> currentUser = this.userService.getUserByEmail(auth.getName());

        return currentUser
                .map(user -> ResponseEntity.ok(
                        sharedFolderService.getFoldersByEmployee(user.getId())
                ))
                .orElse(ResponseEntity.notFound().build());
    }
}
