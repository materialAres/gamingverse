package it.shine.gamingverse.entities.enumerations;

public enum PostalServiceCompany {

    POSTE_ITALIANE("Poste Italiane"),
    DHL("DHL"),
    GLS("GLS"),
    BARTOLINI("Bartolini"),
    SDA("SDA");

    private final String value;

    PostalServiceCompany(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
