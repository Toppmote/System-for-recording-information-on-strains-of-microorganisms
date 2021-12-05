package ru.cheezeapp.service.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.PropertyRepository;
import ru.cheezeapp.entity.PropertyEntity;

import java.util.List;

@Service
public class PropertySearchService {
    @Autowired
    private PropertyRepository propertyRepository;

    /**
     * Поиск всех свойств в репозитории
     *
     * @return список свойств
     */
    @Transactional
    public List<PropertyEntity> findAll() {
        return propertyRepository.findAll();
    }
}