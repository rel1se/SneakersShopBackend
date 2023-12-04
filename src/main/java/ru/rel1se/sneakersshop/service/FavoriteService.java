package ru.rel1se.sneakersshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rel1se.sneakersshop.entity.Favorite;
import ru.rel1se.sneakersshop.entity.Sneaker;
import ru.rel1se.sneakersshop.repository.FavoriteRepository;
import ru.rel1se.sneakersshop.users.User;
import ru.rel1se.sneakersshop.users.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
    @Autowired
    private final FavoriteRepository favoriteRepository;

    @Autowired
    private final UserRepo userRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, UserRepo userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
    }

    public List<Sneaker> getAll(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Favorite favorite = user.getFavorite();
            if (favorite != null) {
                return favorite.getSneakers();
            }
        }
        return List.of();
    }

    public void addToFavorite(Long userId, Sneaker sneaker) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Favorite favorite = user.getFavorite();
            List<Sneaker> sneakers = favorite.getSneakers();
            sneakers.add(sneaker);
            favoriteRepository.save(favorite);
        }
    }

    public void removeFromFavorite(Long userId, Long sneakerId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            Favorite favorite = optionalUser.get().getFavorite();
            List<Sneaker> sneakers = favorite.getSneakers();
            sneakers.removeIf(sneaker -> sneaker.getId().equals(sneakerId));
            favoriteRepository.save(favorite);
        }
    }

}
