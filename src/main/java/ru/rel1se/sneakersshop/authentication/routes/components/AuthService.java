package ru.rel1se.sneakersshop.authentication.routes.components;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rel1se.sneakersshop.authentication.jwt.JwtService;
import ru.rel1se.sneakersshop.authentication.routes.dto.AuthRequestDTO;
import ru.rel1se.sneakersshop.authentication.routes.dto.AuthResponseDTO;
import ru.rel1se.sneakersshop.entity.Cart;
import ru.rel1se.sneakersshop.entity.Favorite;
import ru.rel1se.sneakersshop.repository.CartRepository;
import ru.rel1se.sneakersshop.repository.FavoriteRepository;
import ru.rel1se.sneakersshop.users.Role;
import ru.rel1se.sneakersshop.users.User;
import ru.rel1se.sneakersshop.users.UserRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final CartRepository cartRepository;
    private final FavoriteRepository favoriteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    public User getUserByHttpRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return userRepo
                    .findByEmail(jwtService.getEmail(authHeader.substring(7)))
                    .orElse(null);
        }

        return null;
    }
    public User findUserByJwt(String jwt) {
        return userRepo
                .findByEmail(jwtService.getEmail(jwt))
                .orElse(null);
    }
    public boolean isNotAdmin(HttpServletRequest request) {
        User user = getUserByHttpRequest(request);
        return user == null || user.getRole() != Role.ADMIN;
    }
    public AuthResponseDTO register(AuthRequestDTO requestData) {
        return register(requestData, Role.USER);
    }
    public AuthResponseDTO register(AuthRequestDTO requestData, Role role) {
        Optional<User> dbUser = userRepo.findByEmail(requestData.getEmail());
        if (dbUser.isPresent()) {
            return AuthResponseDTO.builder().jwt(null).status(409).build();
        }

        User user = User.builder()
                .email(requestData.getEmail())
                .password(passwordEncoder.encode(requestData.getPassword()))
                .role(role)
                .build();

        User savedUser = userRepo.save(user);
        Cart cart = new Cart();
        cart.setId(savedUser.getId());
        cart.setUser(savedUser);
        cartRepository.save(cart);

        Favorite favorite = new Favorite();
        favorite.setId(savedUser.getId());
        favorite.setUser(savedUser);
        favoriteRepository.save(favorite);

        return AuthResponseDTO.builder()
                .jwt(jwtService.createToken(user))
                .status(200)
                .build();
    }
    public AuthResponseDTO login(AuthRequestDTO authData) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authData.getEmail(),
                        authData.getPassword()
                )
        );

        User user = userRepo.findByEmail(authData.getEmail())
                .orElseThrow();

        return AuthResponseDTO.builder()
                .jwt(jwtService.createToken(user))
                .status(200)
                .build();
    }
}





