package ajm.spapp.api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Endpoint público";
    }

    @GetMapping("/private")
    public String privateEndpoint(Authentication authentication) {

        return "Autenticado: "
                + authentication.getName();
    }

    @GetMapping("/profile")
    public Authentication profile(
            Authentication authentication) {

        return authentication;
    }

    @GetMapping("/admin")
    public String admin() {
        return "Área de administrador";
    }
}