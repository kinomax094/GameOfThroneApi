package com.git.kinomax094.service;

import com.git.kinomax094.model.House;

import java.util.List;


public interface HouseService {



    House getHouseByName(String name) throws Exception;

    List<House> getHouseInOrderByNumberOfSwornMembers();
}
