package com.git.kinomax094.service;

import com.git.kinomax094.model.House;
import com.git.kinomax094.model.HouseApi;

import java.util.List;

public interface ApiHousesService {

    List<House> getAll();

    List<HouseApi> getAllRaw();


}
