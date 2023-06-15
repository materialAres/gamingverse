package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.OrderDto;
import it.shine.gamingverse.entities.Order;
import it.shine.gamingverse.exceptions.OrderNotFoundException;
import it.shine.gamingverse.mappers.OrderMapper;
import it.shine.gamingverse.repositories.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private Validator validator;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PostalServiceInformationRepository postalServiceRepository;

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Order order = orderMapper.orderDtoToOrder(
                orderDto);

        orderRepository.save(order);

        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public OrderDto getOrderById(Integer id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orders = new ArrayList<>();

        for (Order order : orderRepository.findAll()) {
            orders.add(orderMapper.orderToOrderDto(order));
        }

        return orders;
    }

    @Override
    public OrderDto updateOrder(Integer id, OrderDto orderDto) throws OrderNotFoundException {
        Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Order order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        order.setCustomer(customerRepository.findById(orderDto.getCustomerId()).get());
        order.setProduct(productRepository.findById(orderDto.getProductId()).get());
        order.setTotal(orderDto.getTotal());
        order.setShippingAddress(addressRepository.findById(orderDto.getShippingAddressId()).get());
        order.setBillingAddress(addressRepository.findById(orderDto.getBillingAddressId()).get());
        order.setPostalService(postalServiceRepository.findById(orderDto.getPostalServiceId()).get());
        order.setOrderNotes(orderDto.getOrderNotes());
        order.setUpdatedAt(orderDto.getUpdatedAt());

        orderRepository.save(order);

        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public void deleteOrder(Integer id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        orderRepository.delete(order);
    }

}
