package ru.rel1se.sneakersshop.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rel1se.sneakersshop.authentication.routes.components.AuthService;
import ru.rel1se.sneakersshop.types.StatusCode;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AuthService authService;
    public StatusCode home(HttpServletRequest request) {
        if (authService.isNotAdmin(request)) {
            return StatusCode.create(403);
        }

        return StatusCode.create(200);
    }
}
