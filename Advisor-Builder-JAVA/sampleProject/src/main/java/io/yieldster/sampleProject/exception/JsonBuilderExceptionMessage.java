package io.yieldster.sampleProject.exception;

public enum JsonBuilderExceptionMessage {

    UNABLE_TO_GET_VAULT_DATA("Unable to fetch vault data"),
    UNABLE_TO_GET_STAKED_POOLS("Unable to fetch staked pools");

    private final String description;

    JsonBuilderExceptionMessage(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}