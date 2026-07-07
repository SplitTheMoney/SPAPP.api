package ajm.spapp.api.controller;

import ajm.spapp.api.dto.*;
import ajm.spapp.api.security.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(
            AuthenticationManager authManager,
            JwtService jwtService) {

        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.email(),
                                request.password()
                        )
                );

        UserDetails user =
                (UserDetails) authentication.getPrincipal();

        String token =
                jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}