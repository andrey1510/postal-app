package com.postalservice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum PostalStatus {

    IN_OFFICE("Прибыло в почтовое отделение"),
    OUT_OF_OFFICE("Покинуло почтовое отделение"),
    REGISTERED("Зарегистрировано"),
    RECEIVED("Получено адресатом");

    PostalStatus(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    private final String statusTitle;

    @Override
    @JsonValue
    public String toString() {
        return this.statusTitle;
    }

    public String getStatusTitle() {
        return this.statusTitle;
    }

    @JsonCreator
    public static PostalStatus fromValue(String value) {
        for (PostalStatus type : PostalStatus.values()) {
            if (type.statusTitle.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }

}
