package ru.rel1se.sneakersshop;

import org.springframework.stereotype.Component;
import ru.rel1se.sneakersshop.authentication.routes.components.AuthService;
import ru.rel1se.sneakersshop.authentication.routes.dto.AuthRequestDTO;
import ru.rel1se.sneakersshop.users.Role;


@Component
public class InitialSettings {
    public InitialSettings(
            AuthService authService
    ) {
        authService.register(
                AuthRequestDTO.builder()
                        .email("admin@ya.ru")
                        .password("admin")
                        .build(),
                Role.ADMIN
        );
        authService.register(
                AuthRequestDTO.builder()
                        .email("user@ya.ru")
                        .password("user")
                        .build(),
                Role.USER
        );
    }
}
