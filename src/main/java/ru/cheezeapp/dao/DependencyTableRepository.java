package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.DependencyTableEntity;
import ru.cheezeapp.entity.SubPropertyEntity;

import java.util.Optional;

public interface DependencyTableRepository extends JpaRepository<DependencyTableEntity, Long> {

    Optional<DependencyTableEntity> findByFirstSubPropertyAndSecondSubProperty(SubPropertyEntity first, SubPropertyEntity second);

}
