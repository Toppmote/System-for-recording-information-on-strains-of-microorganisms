package ru.cheezeapp.controller.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.service.rod.RodSearchService;
import ru.cheezeapp.utils.jsonConverter.CatalogsToJson;
import ru.cheezeapp.utils.jsonConverter.ObjectToJsonConverter;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class RodSearchController {

    @Autowired
    private RodSearchService rodSearchService;

    /**
     * Метод поиска всех родов и формирования их в список, вывод в виде JSON
     *
     * @return JSON списка родов
     */
    @GetMapping("/rods")
    public String getListOfRods() {
        List<RodStrainEntity> rodsStrainEntity = rodSearchService.findAllNonDeletedRods();
        log.info("[GET /rods]\tReturn all non deleted rods by list");
        return CatalogsToJson.rodCatalogToJson(rodsStrainEntity);
    }

    /**
     * Метод поиска всех родов и формирования их в список, вывод в виде JSON
     *
     * @return JSON списка родов
     */
    @GetMapping("/deleted_rods")
    public String getListOfDeletedRods() {
        List<RodStrainEntity> rodsStrainEntity = rodSearchService.findAllDeletedRods();
        log.info("[GET /deleted_rods]\tReturn all deleted rods by list");
        return CatalogsToJson.rodCatalogToJson(rodsStrainEntity);
    }

    @GetMapping("/rods/{id}")
    public String getRodById(@PathVariable Long id) {
        RodStrainEntity rod = rodSearchService.findById(id);
        return ObjectToJsonConverter.rodToJson(rod);
    }

    @PostMapping("/rods/searchByName")
    public String getListOfRodsByNameContaining(@RequestBody(required = false) String name) {
        if (name == null)
            return getListOfRods();
        List<RodStrainEntity> rods = rodSearchService.findByNameContaining(name);
        log.info("[GET /rods/searchByName]\tReturn list of rods by containing name");
        List<String> returnValue = new ArrayList<>();
        for (RodStrainEntity rod : rods) {
            returnValue.add(ObjectToJsonConverter.rodToJson(rod));
        }
        return returnValue.toString();
    }

}
