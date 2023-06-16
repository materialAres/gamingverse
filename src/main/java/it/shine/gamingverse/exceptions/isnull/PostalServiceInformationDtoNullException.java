package it.shine.gamingverse.exceptions.isnull;

public class PostalServiceInformationDtoNullException extends Exception {

    public PostalServiceInformationDtoNullException() {
        super("PostalServiceInformation dto is null or empty");
    }

}