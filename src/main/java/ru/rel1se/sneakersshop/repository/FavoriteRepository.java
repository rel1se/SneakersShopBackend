package ru.rel1se.sneakersshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rel1se.sneakersshop.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
