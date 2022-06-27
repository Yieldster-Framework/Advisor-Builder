package io.yieldster.sampleProject.exception;

public enum JsonBuilderExceptionMessage {

    UNABLE_TO_GET_VAULT_DATA("Unable to fetch vault data"),
    UNABLE_TO_GET_STAKED_POOLS("Unable to fetch staked pools"), UNABLE_TO_GET_BALANCE("Unable to get balance"), UNABLE_TO_GET_TOKENS("Unable to get reward tokens"), UNABLE_TO_GET_PRICE("Unable to get token price"), UNABLE_TO_PROCESS("Unable to process");

    private final String description;

    JsonBuilderExceptionMessage(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}