package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.OrderDto;
import it.shine.gamingverse.entities.Order;
import it.shine.gamingverse.exceptions.listempty.OrderListEmptyException;
import it.shine.gamingverse.exceptions.isnull.OrderDtoNullException;
import it.shine.gamingverse.exceptions.notfound.OrderNotFoundException;
import it.shine.gamingverse.mappers.OrderMapper;
import it.shine.gamingverse.repositories.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    public OrderDto addOrder(OrderDto orderDto) throws OrderDtoNullException {
        if (ObjectUtils.isEmpty(orderDto)) {
            throw new OrderDtoNullException();
        }

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
    public List<OrderDto> getAllOrders() throws OrderListEmptyException {
        List<Order> orders = orderRepository.findAll();

        if (orders.isEmpty()) {
            throw new OrderListEmptyException();
        }

        List<OrderDto> ordersDto = new ArrayList<>();

        for (Order order : orders) {
            ordersDto.add(orderMapper.orderToOrderDto(order));
        }

        return ordersDto;
    }


    @Override
    public OrderDto updateOrder(Integer id, OrderDto orderDto) throws OrderDtoNullException {
        if (ObjectUtils.isEmpty(orderDto)) {
            throw new OrderDtoNullException();
        }

        Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        orderDto.setId(id);
        Order order = orderMapper.orderDtoToOrder(orderDto);

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
