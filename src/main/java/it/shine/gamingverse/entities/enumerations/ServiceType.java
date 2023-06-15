package it.shine.gamingverse.entities.enumerations;

public enum ServiceType {

    NORMAL("Normal"),
    EXPRESS("Express"),
    PREMIUM("Premium");

    private final String value;

    ServiceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
