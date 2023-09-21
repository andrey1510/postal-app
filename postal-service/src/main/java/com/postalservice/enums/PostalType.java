package com.postalservice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PostalType {

    LETTER("Письмо"),
    PACKAGE("Посылка"),
    PARCEL("Бандероль"),
    POSTCARD("Открытка");

    PostalType(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    private final String typeTitle;

    @Override
    @JsonValue
    public String toString() {
        return this.typeTitle;
    }

    public String getTypeTitle() {
        return this.typeTitle;
    }

    @JsonCreator
    public static PostalType fromValue(String value) {
        for (PostalType type : PostalType.values()) {
            if (type.typeTitle.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }

}
