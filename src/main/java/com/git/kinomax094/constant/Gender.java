package com.git.kinomax094.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.omg.CosNaming.NamingContextPackage.NotFoundHelper;
import org.springframework.web.client.HttpClientErrorException;

public enum Gender {

    MALE("male"),
    FEMALE("female");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }


    @JsonCreator
    public static Gender values(String gender) throws NoSuchFieldException {
        Gender result = null;
        for(Gender value : values()) {
            if (value.getValue().equals(gender)) {
                result = value;
            }
        }
        if(result == null) {
            throw new NoSuchFieldException();

        }
        return result;
    }
}
