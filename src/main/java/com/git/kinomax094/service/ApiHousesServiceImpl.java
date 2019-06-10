package com.git.kinomax094.service;

import com.git.kinomax094.model.Character;
import com.git.kinomax094.model.House;
import com.git.kinomax094.model.HouseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ApiHousesServiceImpl implements ApiHousesService {

    @Autowired
    private ApiCharactersServiceImpl service;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${GAME_OF_THRONE_HOUSES}")
    private String housesUrl;

    @Value("${HEAD_NAME_USER_AGENT}")
    private String userAgent;

    @Value("${HEAD_VALUE_FOR_USER_AGENT}")
    private String valueForUserAgent;

    @Override
    public List<House> getAll() {
        List<House> result;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.add(userAgent, valueForUserAgent);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<HouseApi>> response = restTemplate.exchange(housesUrl,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<HouseApi>>() {
                });
        result = mappedApiHouseOnHouse(response.getBody());

        return result;
    }

    @Override
    public List<HouseApi> getAllRaw() {
        List<House> result;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.add(userAgent, valueForUserAgent);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<HouseApi>> response = restTemplate.exchange(housesUrl,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<HouseApi>>() {
                });
        return response.getBody();
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
                house.setCurrentLord(service.getOneCharacter(houseApi.getCurrentLord()));
            }
            house.setAncestralWeapons(houseApi.getAncestralWeapons());
            List<Character> listSwornMembers = new ArrayList<>();
            if (houseApi.getSwornMembers().size() > 0) {
                for (String swornMember : houseApi.getSwornMembers()) {
                    if (!swornMember.equals("")) {
                        listSwornMembers.add(service.getOneCharacter(swornMember));
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
            house.setCurrentLord(service.getOneCharacter(houseApi.getCurrentLord()));
        }
        house.setAncestralWeapons(houseApi.getAncestralWeapons());
        List<Character> listSwornMembers = new ArrayList<>();
        if (houseApi.getSwornMembers().size() > 0) {
            for (String swornMember : houseApi.getSwornMembers()) {
                if (!swornMember.equals("")) {
                    listSwornMembers.add(service.getOneCharacter(swornMember));
                }
            }
        }
        house.setSwornMembers(listSwornMembers);

        return house;
    }
}
