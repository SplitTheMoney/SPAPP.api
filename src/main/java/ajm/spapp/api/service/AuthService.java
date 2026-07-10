package ajm.spapp.api.service;

import ajm.spapp.api.dto.LoginRequestDTO;
import ajm.spapp.api.dto.LoginResponseDTO;
import ajm.spapp.api.model.User;
import ajm.spapp.api.repository.UserRepository;
import ajm.spapp.api.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(
            AuthenticationManager authManager,
            UserRepository userRepository,
            JwtService jwtService) {

        this.authManager = authManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO Login(LoginRequestDTO dto) {
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.email(),
                                dto.password()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token =
                jwtService.generateToken(userDetails);

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new LoginResponseDTO(token, user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
