package com.git.kinomax094.controller;

import com.git.kinomax094.model.House;
import com.git.kinomax094.model.HouseApi;
import com.git.kinomax094.service.ApiHousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiHouseController {

    @Autowired
    ApiHousesService service;

    @GetMapping("/getAll")
    public List<House> getAll() {
        List<House> result = service.getAll();
        return result;
    }

    @GetMapping("/getAllRaw")
    public List<HouseApi> getAllRaw() {

        List<HouseApi> result = service.getAllRaw();
        return result;
    }





}
