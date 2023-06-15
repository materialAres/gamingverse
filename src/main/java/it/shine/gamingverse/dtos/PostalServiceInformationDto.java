package it.shine.gamingverse.dtos;

import it.shine.gamingverse.entities.PostalServiceInformation;
import it.shine.gamingverse.entities.enumerations.DeliveryStatus;
import it.shine.gamingverse.entities.enumerations.PostalServiceCompany;
import it.shine.gamingverse.entities.enumerations.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostalServiceInformationDto {

    private Integer id;
    private PostalServiceCompany postalServiceCompany;
    private String trackingNumber;
    private String trackingLink;
    private LocalDate estimatedDelivery;
    private ServiceType serviceType;
    private DeliveryStatus deliveryStatus;
    private String additionalInformation;

    public PostalServiceInformationDto(PostalServiceInformation postalServiceInformation) {
        this.id = postalServiceInformation.getId();
        this.postalServiceCompany = postalServiceInformation.getPostalServiceCompany();
        this.trackingNumber = postalServiceInformation.getTrackingNumber();
        this.trackingLink = postalServiceInformation.getTrackingLink();
        this.estimatedDelivery = postalServiceInformation.getEstimatedDelivery();
        this.serviceType = postalServiceInformation.getServiceType();
        this.deliveryStatus = postalServiceInformation.getDeliveryStatus();
        this.additionalInformation = postalServiceInformation.getAdditionalInformation();
    }

}
