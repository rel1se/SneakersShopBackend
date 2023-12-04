package ru.rel1se.sneakersshop.authentication.routes.components;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rel1se.sneakersshop.users.User;
import ru.rel1se.sneakersshop.users.UserRepo;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElse(null);
    }
}
