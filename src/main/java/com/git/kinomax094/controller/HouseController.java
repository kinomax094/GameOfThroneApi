package com.git.kinomax094.controller;


import com.git.kinomax094.model.House;
import com.git.kinomax094.service.HouseService;
import com.git.kinomax094.validation.NameValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseController {

    @Autowired
    private HouseService service;



    @GetMapping("/getHouseByName/{name}")
    public House getHouseByName(@PathVariable String name) throws Exception {
        NameValidation validation = new NameValidation();
        validation.wrongPattern(name);
        House result = service.getHouseByName(name);
        validation.notFoundName(result);
        return result;
    }

}
