package ru.rel1se.sneakersshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rel1se.sneakersshop.entity.Sneaker;
import ru.rel1se.sneakersshop.exception.UserNotFoundException;
import ru.rel1se.sneakersshop.repository.SneakerRepository;

import java.util.List;

;


@Service
public class SneakerService {
    @Autowired
    private SneakerRepository sneakerRepository;

    public List<Sneaker> getAllSneakers(){
        return sneakerRepository.findAll();
    }
    public Sneaker getOne(Long id) throws Exception{
        Sneaker sneaker = sneakerRepository.findById(id).get();
        if (sneaker == null){
            throw new UserNotFoundException("Товар не найден");
        }
        return sneaker;
    }
    public Sneaker create(Sneaker sneaker){
        return sneakerRepository.save(sneaker);
    }
    public Sneaker update(Long id, Sneaker updatedSneaker){
        Sneaker sneaker = sneakerRepository.findById(id).get();
        sneaker.setTitle(updatedSneaker.getTitle());
        sneaker.setPrice(updatedSneaker.getPrice());
        sneaker.setImageUrl(updatedSneaker.getImageUrl());
        return sneakerRepository.save(sneaker);
    }
    public void delete(Long id){
        sneakerRepository.deleteById(id);
    }
}
