package com.git.kinomax094.model;

import java.util.List;

public class HouseApi {

    private String name;
    private String region;
    private String coatOfArms;
    private String words;
    private List<String> titles;
    private List<String> seats;
    private String currentLord;
    private String heir;
    private List<String> ancestralWeapons;
    private List<String> swornMembers;

    public HouseApi() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCoatOfArms() {
        return coatOfArms;
    }

    public void setCoatOfArms(String coatOfArms) {
        this.coatOfArms = coatOfArms;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public String getCurrentLord() {
        return currentLord;
    }

    public void setCurrentLord(String currentLord) {
        this.currentLord = currentLord;
    }

    public String getHeir() {
        return heir;
    }

    public void setHeir(String heir) {
        this.heir = heir;
    }

    public List<String> getAncestralWeapons() {
        return ancestralWeapons;
    }

    public void setAncestralWeapons(List<String> ancestralWeapons) {
        this.ancestralWeapons = ancestralWeapons;
    }

    public List<String> getSwornMembers() {
        return swornMembers;
    }

    public void setSwornMembers(List<String> swornMembers) {
        this.swornMembers = swornMembers;
    }
}
