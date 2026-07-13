package ajm.spapp.api.controller;

import ajm.spapp.api.dto.*;
import ajm.spapp.api.model.User;
import ajm.spapp.api.repository.UserRepository;
import ajm.spapp.api.security.JwtService;
import ajm.spapp.api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {

        return ResponseEntity.ok(authService.Login(dto));
    }
}