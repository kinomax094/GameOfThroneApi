package com.git.kinomax094.service;

import com.git.kinomax094.model.Character;
import com.git.kinomax094.model.House;
import com.git.kinomax094.model.HouseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HousesServiceImpl implements HouseService {

    @Autowired
    private ApiHousesService service;

    @Autowired
    private ApiCharacterService serviceCharacter;


    @Override
    public House getHouseByName(String name) throws Exception {
        name = name.replaceAll("_", " ");
        List<HouseApi> all = service.getAllRaw();
        House result = null;
        for (HouseApi houseApi : all) {
            if (houseApi.getName().equals(name)) {
                result = mappedApiHouseOnHouse(houseApi);
            }
        }

            return result;
    }

    @Override
    public List<House> getHouseInOrderByNumberOfSwornMembers() {
        List<House> result = new ArrayList<>();
        result = service.getAll();
        result = result.stream()
                .sorted((n, m) -> Integer.compare(n.getSwornMembers().size(), m.getSwornMembers().size()))
                .collect(Collectors.toList());

        return result;
    }


    private List<House> mappedApiHouseOnHouse(List<HouseApi> list) {
        List<House> result = new ArrayList<>();
        for (HouseApi houseApi : list) {
            House house = new House();
            house.setName(houseApi.getName());
            house.setRegion(houseApi.getRegion());
            house.setCoatOfArms(houseApi.getCoatOfArms());
            house.setWords(houseApi.getWords());
            house.setTitles(houseApi.getTitles());
            house.setSeats(houseApi.getSeats());
            if (!houseApi.getCurrentLord().equals("")) {
                house.setCurrentLord(serviceCharacter.getOneCharacter(houseApi.getCurrentLord()));
            }
            house.setAncestralWeapons(houseApi.getAncestralWeapons());
            List<Character> listSwornMembers = new ArrayList<>();
            if (houseApi.getSwornMembers().size() > 0) {
                for (String swornMember : houseApi.getSwornMembers()) {
                    if (!swornMember.equals("")) {
                        listSwornMembers.add(serviceCharacter.getOneCharacter(swornMember));
                    }
                }
            }
            house.setSwornMembers(listSwornMembers);
            result.add(house);
        }

        return result;
    }

    private House mappedApiHouseOnHouse(HouseApi houseApi) {

        House house = new House();
        house.setName(houseApi.getName());
        house.setRegion(houseApi.getRegion());
        house.setCoatOfArms(houseApi.getCoatOfArms());
        house.setWords(houseApi.getWords());
        house.setTitles(houseApi.getTitles());
        house.setSeats(houseApi.getSeats());
        if (!houseApi.getCurrentLord().equals("")) {
            house.setCurrentLord(serviceCharacter.getOneCharacter(houseApi.getCurrentLord()));
        }
        house.setAncestralWeapons(houseApi.getAncestralWeapons());
        List<Character> listSwornMembers = new ArrayList<>();
        if (houseApi.getSwornMembers().size() > 0) {
            for (String swornMember : houseApi.getSwornMembers()) {
                if (!swornMember.equals("")) {
                    listSwornMembers.add(serviceCharacter.getOneCharacter(swornMember));
                }

            }
        }
        house.setSwornMembers(listSwornMembers);

        return house;
    }
}
