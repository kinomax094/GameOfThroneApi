package com.git.kinomax094.service;

import com.git.kinomax094.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@Service
public class ApiCharactersServiceImpl implements ApiCharacterService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${GAME_OF_THRONE_HOUSES}")
    private String housesUrl;

    @Value("${HEAD_NAME_USER_AGENT}")
    private String userAgent;

    @Value("${HEAD_VALUE_FOR_USER_AGENT}")
    private String valueForUserAgent;


    @Override
    public Character getOneCharacter(final String url) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(userAgent, valueForUserAgent);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Character> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Character.class);
        return response.getBody();
    }

}
