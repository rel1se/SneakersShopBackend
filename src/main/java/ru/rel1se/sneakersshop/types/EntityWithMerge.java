package ru.rel1se.sneakersshop.types;

public interface EntityWithMerge<T> {
    void merge(T inputEntity);
}
