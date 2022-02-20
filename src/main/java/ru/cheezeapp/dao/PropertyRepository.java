package ru.cheezeapp.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.PropertyEntity;

import java.util.List;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {

    /**
     * Метод поиска всех неудаленных свойств
     *
     * @param sortingParameter параметр сортировки
     * @return список неудаленных свойств
     */
    List<PropertyEntity> findAllByDeletedIsFalse(Sort sortingParameter);

    /**
     * Метод поиска всех удаленных свойств
     *
     * @param sortingParameter параметр сортировки
     * @return список удаленных свойств
     */
    List<PropertyEntity> findAllByDeletedIsTrue(Sort sortingParameter);

}
