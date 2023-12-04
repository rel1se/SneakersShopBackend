package ru.rel1se.sneakersshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rel1se.sneakersshop.entity.Sneaker;
import ru.rel1se.sneakersshop.exception.UserNotFoundException;
import ru.rel1se.sneakersshop.service.SneakerService;

@RestController
@RequestMapping("/sneakers")
public class SneakerController {
    @Autowired
    SneakerService sneakerService;
    @GetMapping("/all")
    public ResponseEntity getAllSneakers(){
        try{
            return ResponseEntity.ok(sneakerService.getAllSneakers());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при получении всех товаров");
        }
    }
    @GetMapping
    public ResponseEntity getOneSneaker(@RequestParam Long id) {
        try{
            return ResponseEntity.ok(sneakerService.getOne(id));
        } catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при получении одного товара");
        }
    }
    @PostMapping
    public ResponseEntity createSneaker(@RequestBody Sneaker sneaker){
        try {
            return ResponseEntity.ok(sneakerService.create(sneaker));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при создании товара");
        }
    }

    @PutMapping
    public ResponseEntity updateSneaker(@RequestParam Long sneakerId, @RequestBody Sneaker sneaker) {
        try {
            return ResponseEntity.ok(sneakerService.update(sneakerId,sneaker));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при обновлении данных товара");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteSneaker(@RequestParam Long sneakerId){
        try{
            sneakerService.delete(sneakerId);
            return ResponseEntity.ok("Товар успешно удален");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка при удалении товара");
        }
    }
}

