package ru.rel1se.sneakersshop.authentication.routes.components;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rel1se.sneakersshop.authentication.routes.dto.AuthRequestDTO;
import ru.rel1se.sneakersshop.authentication.routes.dto.AuthResponseDTO;
import ru.rel1se.sneakersshop.users.User;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody AuthRequestDTO registerData) {
        AuthResponseDTO response = authService.register(registerData);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody AuthRequestDTO authData
    ) {
        return ResponseEntity.ok(authService.login(authData));
    }

    @GetMapping(value = "/users", params = {"jwt"})
    public User findUserByJwt(@RequestParam String jwt) {
        return authService.findUserByJwt(jwt);
    }
}
