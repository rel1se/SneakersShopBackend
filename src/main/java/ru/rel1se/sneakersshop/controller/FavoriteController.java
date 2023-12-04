package ru.rel1se.sneakersshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rel1se.sneakersshop.entity.Sneaker;
import ru.rel1se.sneakersshop.service.FavoriteService;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity getFavoriteItems(@RequestParam Long userId){
        try{
            return ResponseEntity.ok(favoriteService.getAll(userId));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при получении всех товаров из избранного");
        }
    }

    @PostMapping
    public ResponseEntity addToFavorite(@RequestParam Long userId, @RequestBody Sneaker sneaker) {
        try{
            favoriteService.addToFavorite(userId, sneaker);
            return ResponseEntity.ok("Товар добавлен в избранное");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при добавлении в избранное");
        }
    }

    @DeleteMapping
    public ResponseEntity removeFromFavorite(@RequestParam Long userId, @RequestParam Long sneakerId) {
        try {
            favoriteService.removeFromFavorite(userId, sneakerId);
            return ResponseEntity.ok("Товар удален из избранного");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при удалении товара из избранного");
        }
    }
}
