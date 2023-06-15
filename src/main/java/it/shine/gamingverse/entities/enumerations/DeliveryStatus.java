package it.shine.gamingverse.entities.enumerations;

public enum DeliveryStatus {

    DISPATCHED("Dispatched"),
    SHIPPED("Shipped"),
    IN_TRANSIT("In transit"),
    DELIVERED("Delivered"),
    NOT_DELIVERED("Not delivered"),
    RETURNED("Returned");

    private final String value;

    DeliveryStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
