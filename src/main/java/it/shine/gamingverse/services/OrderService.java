package it.shine.gamingverse.services;


import it.shine.gamingverse.dtos.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto addOrder(OrderDto order);

    OrderDto getOrderById(Integer id) throws Exception;

    List<OrderDto> getAllOrders();

    OrderDto updateOrder(Integer id, OrderDto order) throws Exception;

    void deleteOrder(Integer id) throws Exception;

}
