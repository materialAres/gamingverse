package it.shine.gamingverse.services;


import it.shine.gamingverse.dtos.OrderDto;
import it.shine.gamingverse.exceptions.listempty.OrderListEmptyException;
import it.shine.gamingverse.exceptions.isnull.OrderDtoNullException;

import java.util.List;

public interface OrderService {

    OrderDto addOrder(OrderDto order) throws OrderDtoNullException;

    OrderDto getOrderById(Integer id) throws Exception;

    List<OrderDto> getAllOrders() throws OrderListEmptyException;

    OrderDto updateOrder(Integer id, OrderDto order) throws Exception;

    void deleteOrder(Integer id) throws Exception;

}
