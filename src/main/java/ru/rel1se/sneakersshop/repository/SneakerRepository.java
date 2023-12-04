package ru.rel1se.sneakersshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rel1se.sneakersshop.entity.Sneaker;

public interface SneakerRepository extends JpaRepository<Sneaker, Long> {
}

