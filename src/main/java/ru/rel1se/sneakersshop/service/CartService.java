package ru.rel1se.sneakersshop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rel1se.sneakersshop.entity.Cart;
import ru.rel1se.sneakersshop.entity.Sneaker;
import ru.rel1se.sneakersshop.repository.CartRepository;
import ru.rel1se.sneakersshop.users.User;
import ru.rel1se.sneakersshop.users.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final UserRepo userRepository;

    public CartService(CartRepository cartRepository, UserRepo userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public List<Sneaker> getAll(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Cart cart = user.getCart();
            if (cart != null) {
                return cart.getSneakers();
            }
        }
        return List.of();
    }

    public void addToCart(Long userId, Sneaker sneaker) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Cart cart = user.getCart();

            List<Sneaker> sneakers = cart.getSneakers();
            sneakers.add(sneaker);

            cartRepository.save(cart);
        }
    }

    public void removeFromCart(Long userId, Long sneakerId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            Cart cart = optionalUser.get().getCart();
            List<Sneaker> sneakers = cart.getSneakers();
            sneakers.removeIf(sneaker -> sneaker.getId().equals(sneakerId));
            cartRepository.save(cart);
        }
    }
    public void removeAll(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Cart cart = optionalUser.get().getCart();
            List<Sneaker> sneakers = cart.getSneakers();
            sneakers.clear();
            cartRepository.save(cart);
        }
    }
}

