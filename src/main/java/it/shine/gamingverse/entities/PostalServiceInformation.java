package it.shine.gamingverse.entities;

import it.shine.gamingverse.entities.enumerations.DeliveryStatus;
import it.shine.gamingverse.entities.enumerations.PostalServiceCompany;
import it.shine.gamingverse.entities.enumerations.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "postal_services", schema = "public", catalog = "gamingverse")
public class PostalServiceInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private PostalServiceCompany postalServiceCompany;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "tracking_link")
    private String trackingLink;

    @Column(name = "estimated_delivery")
    private LocalDate estimatedDelivery;

    @Column(name = "service_type")
    private ServiceType serviceType;

    @Column(name = "delivery_status")
    private DeliveryStatus deliveryStatus;

    @Column(name = "additional_information")
    private String additionalInformation;

}
