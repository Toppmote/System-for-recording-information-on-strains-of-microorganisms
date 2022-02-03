package ru.cheezeapp.controller.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.entity.*;
import ru.cheezeapp.service.subproperty.SubpropertySearchService;
import ru.cheezeapp.utils.jsonConverter.CatalogsToJson;
import ru.cheezeapp.utils.jsonConverter.ObjectToJsonConverter;

import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с поиском подсвойств.
 */
@RestController
@Slf4j
public class SubpropertySearchController {

    @Autowired
    private SubpropertySearchService subpropertySearchService;

    /**
     * Метод поиска списка всех подсвойств и передачи его в виде JSON
     *
     * @return JSON списка подсвойств
     */
    @GetMapping("/subproperties")
    public String getSubpropertyList() {
        List<SubPropertyEntity> subproperties = subpropertySearchService.findAll();
        log.info("[GET /subproperties]\tReturn a list of subproperties");
        return CatalogsToJson.subpropertyCatalogToJson(subproperties);
    }

    /**
     * Метод поиска подсвойства по id и передачи его в виде JSON
     *
     * @param id id подсвойства
     * @return JSON подсвойства
     */
    //TODO убрать заглушку JSON
    @GetMapping("/subproperties/{id}")
    public String getSubpropertyById(@PathVariable Long id) {
        SubPropertyEntity subproperty = subpropertySearchService.findById(id);
        log.info("[GET /subproperties/" + id + "]\tReturn subproperty with id " + id);
        return ObjectToJsonConverter.subpropertyToJson(subproperty);
    }

    /**
     * Метод поиска подсвойств по заданному ID свойства и передачи его в виде JSON
     *
     * @param id ID свойства
     * @return JSON списка подсвойств
     */
    @GetMapping("/subproperties/properties/{id}")
    public String getListOfSubpropertiesByPropertyId(@PathVariable Long id) {
        List<SubPropertyEntity> subproperties = subpropertySearchService.findSubpropertiesByPropertyId(id);
        log.info("[GET /subproperties/properties/{id}]\tReturn list of subproperty by ID's property");
        return CatalogsToJson.subpropertyCatalogToJson(subproperties);
    }

}