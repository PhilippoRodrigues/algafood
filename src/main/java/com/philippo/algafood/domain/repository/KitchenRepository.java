package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    List<Kitchen> findAllByNameContaining(String name);

    Optional<Kitchen> findByName(String kitchen);

    Boolean existsByName(String name);
}
