package ru.rel1se.sneakersshop.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rel1se.sneakersshop.types.StatusCode;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @GetMapping
    public ResponseEntity<StatusCode> home(
            @NonNull HttpServletRequest request
    ) {
        StatusCode statusCode = adminService.home(request);
        return ResponseEntity.status(statusCode.getStatus()).body(statusCode);
    }
}
