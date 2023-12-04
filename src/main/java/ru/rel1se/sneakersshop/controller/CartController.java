package ru.rel1se.sneakersshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rel1se.sneakersshop.entity.Sneaker;
import ru.rel1se.sneakersshop.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping
    public ResponseEntity getCartItems(@RequestParam Long userId){
        try{
            return ResponseEntity.ok(cartService.getAll(userId));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при получении всех товаров из корзины");
        }
    }

    @PostMapping
    public ResponseEntity addToCart(@RequestParam Long userId, @RequestBody Sneaker sneaker) {
        try{
            cartService.addToCart(userId, sneaker);
            return ResponseEntity.ok("Товар добавлен в корзину");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при добавлении в корзину");
        }
    }

    @DeleteMapping
    public ResponseEntity removeFromCart(@RequestParam Long userId, @RequestParam Long sneakerId) {
        try {
            cartService.removeFromCart(userId, sneakerId);
            return ResponseEntity.ok("Товар удален из корзины");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при удалении товара из корзины");
        }

    }
    @DeleteMapping("/all")
    public ResponseEntity removeAll(@RequestParam Long userId){
        try {
            cartService.removeAll(userId);
            return ResponseEntity.ok("Корзина очищена");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при удалении товара из корзины");
        }
    }
}