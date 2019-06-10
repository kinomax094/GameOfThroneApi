package com.git.kinomax094.validation;

import com.git.kinomax094.exeption.WrongNameException;
import com.git.kinomax094.exeption.WrongPatternException;
import com.git.kinomax094.model.House;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class NameValidation {


    public void notFoundName(House houses) {
        if (houses == null) {
            throw new WrongNameException("Pod podaną nazwą nie znaleziono rodu (q : p)");
        }
    }

    public void wrongPattern(String name) {
        if (!name.contains("_")) {
            throw new WrongPatternException("Podano zły format nazwy");
        }
    }

}
