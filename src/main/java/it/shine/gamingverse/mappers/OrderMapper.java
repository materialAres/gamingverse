package it.shine.gamingverse.mappers;

import it.shine.gamingverse.dtos.OrderDto;
import it.shine.gamingverse.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // TEST
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    //

    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "shippingAddressId", target = "shippingAddress.id")
    @Mapping(source = "billingAddressId", target = "billingAddress.id")
    @Mapping(source = "postalServiceId", target = "postalService.id")
    Order orderDtoToOrder(OrderDto orderDto);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "shippingAddress.id", target = "shippingAddressId")
    @Mapping(source = "billingAddress.id", target = "billingAddressId")
    @Mapping(source = "postalService.id", target = "postalServiceId")
    OrderDto orderToOrderDto(Order order);

}
