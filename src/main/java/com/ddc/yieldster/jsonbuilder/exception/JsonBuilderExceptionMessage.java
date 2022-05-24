package com.ddc.yieldster.jsonbuilder.exception;

public enum JsonBuilderExceptionMessage {

    UNABLE_TO_GET_VAULT_DATA("Unable to fetch vault data"),
    UNABLE_TO_GET_TOKEN_PRICE("Unable to fetch token price"),
    UNABLE_TO_GET_BALANCE("Unable to fetch token balance"),
    UNABLE_TO_GET_NAV("Unable to fetch vault NAV");

    private final String description;

    JsonBuilderExceptionMessage(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}