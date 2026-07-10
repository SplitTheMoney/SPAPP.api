package ajm.spapp.api.controller;

import ajm.spapp.api.dto.NewUserRequestDTO;
import ajm.spapp.api.model.Role;
import ajm.spapp.api.model.User;
import ajm.spapp.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody NewUserRequestDTO newUserRequestDTO) {
        User newUser = new User();

        newUser.setName(newUserRequestDTO.getName());
        newUser.setEmail(newUserRequestDTO.getEmail());
        newUser.setPasswordHash(passwordEncoder.encode(newUserRequestDTO.getPassword()));
        newUser.setRole(newUserRequestDTO.getRole());

        return userService.createUser(newUser);
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        Role userRole = Role.valueOf(role.toUpperCase());

        return userService.getUsersByRole(userRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody NewUserRequestDTO newUserRequestDTO) {
        Optional<User> oldUser = userService.getUserById(id);

        if (oldUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User userToUpdate = oldUser.get();

        userToUpdate.setName(newUserRequestDTO.getName());
        userToUpdate.setEmail(newUserRequestDTO.getEmail());
        userToUpdate.setPasswordHash(passwordEncoder.encode(newUserRequestDTO.getPassword()));
        userToUpdate.setRole(newUserRequestDTO.getRole());

        return ResponseEntity.ok(userService.updateUser(userToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> oldUser = userService.getUserById(id);

        if (oldUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }
}
