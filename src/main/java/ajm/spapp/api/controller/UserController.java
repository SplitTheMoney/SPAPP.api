package ajm.spapp.api.controller;

import ajm.spapp.api.dto.NewUserRequestDTO;
import ajm.spapp.api.dto.UserResponseDTO;
import ajm.spapp.api.model.Role;
import ajm.spapp.api.model.User;
import ajm.spapp.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody NewUserRequestDTO newUserRequestDTO) {
        return ResponseEntity.ok(userService.createUser(newUserRequestDTO));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        Role userRole = Role.valueOf(role.toUpperCase());

        return userService.getUsersByRole(userRole);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody NewUserRequestDTO newUserRequestDTO) {
        return ResponseEntity.ok(userService.updateUser(id, newUserRequestDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
