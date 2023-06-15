package it.shine.gamingverse.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.shine.gamingverse.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderDto {

    private Integer id;

    private Integer customerId;

    private Integer productId;

    private BigDecimal total;

    private Integer shippingAddressId;

    private Integer billingAddressId;

    private Integer postalServiceId;

    private String orderNotes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.customerId = order.getCustomer().getId();
        this.productId = order.getProduct().getId();
        this.total = order.getTotal();
        this.shippingAddressId = order.getShippingAddress().getId();
        this.billingAddressId = order.getBillingAddress().getId();
        this.postalServiceId = order.getPostalService().getId();
        this.orderNotes = order.getOrderNotes();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
    }

}
