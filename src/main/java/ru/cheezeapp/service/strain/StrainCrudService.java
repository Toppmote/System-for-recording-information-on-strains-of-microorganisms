package ru.cheezeapp.service.strain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.StrainRepository;
import ru.cheezeapp.entity.StrainEntity;

import java.util.Optional;

/**
 * Сервис с Crud операциями для штамма
 */
@Service
public class StrainCrudService {

    @Autowired
    StrainRepository strainRepository;

    /**
     * Процедура добавления штамма в БД. Также добавляем все параметры штамма.
     *
     * @param strain штамм для добавления
     */
    @Transactional
    public void addStrain(StrainEntity strain) {
        strainRepository.save(strain);
    }

    /**
     * Процедура мягкого удаления штамма по ID.
     * Помечаем штамм как удаленный и обновляем его в БД.
     *
     * @param id ID штамма для удаления
     */
    @Transactional
    public void softDeletionOfStrainById(Long id) {
        Optional<StrainEntity> strainEntity = strainRepository.findById(id);
        if (strainEntity.isPresent()) {
            strainEntity.get().setDeleted(true);
            updateStrain(strainEntity.get());
        } else throw new RuntimeException("Штамм не существует");
    }

    /**
     * Процедура полного удаления штамма из БД по ID.
     * Удаляем как штамм, так и все фактические и функциональные свойства через соответствующие сервисы.
     *
     * @param id ID штамма для удаления
     */
    @Transactional
    public void hardDeletionOfStrainById(Long id) {
        Optional<StrainEntity> strainEntity = strainRepository.findById(id);
        if (strainEntity.isPresent()) {
            strainRepository.deleteById(id);
        } else throw new RuntimeException("Штамм не существует");
    }

    /**
     * Процедура обновления штамма в БД. Также обновляем все параметры штамма.
     *
     * @param strain штамм для обновления
     */
    @Transactional
    public void updateStrain(StrainEntity strain) {
        Optional<StrainEntity> strainEntity = strainRepository.findById(strain.getId());
        if (strainEntity.isPresent()) {
            strainRepository.save(strain);
        }
    }

    /**
     * Процедура удаления всех штаммов с пометкой "Удален".
     */
    @Transactional
    public void harDeleteAllStrains() {
        strainRepository.deleteAllByDeletedIsTrue();
    }

}
