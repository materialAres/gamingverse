package it.shine.gamingverse.exceptions.listempty;

public class PostalServiceInformationListEmptyException extends Exception {

    public PostalServiceInformationListEmptyException() {
        super("PostalServiceInformation list is empty");
    }

}
