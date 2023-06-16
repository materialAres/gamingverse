package it.shine.gamingverse.controllers;

import it.shine.gamingverse.controllers.utils.CheckControllerError;
import it.shine.gamingverse.dtos.OrderDto;
import it.shine.gamingverse.exceptions.listempty.OrderListEmptyException;
import it.shine.gamingverse.exceptions.isnull.OrderDtoNullException;
import it.shine.gamingverse.exceptions.notfound.OrderNotFoundException;
import it.shine.gamingverse.services.OrderServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderDto orderDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(orderDto));

        } catch (ConstraintViolationException e) {
            Map<String, String> errors = CheckControllerError.checkControllerErrors(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

        } catch (OrderDtoNullException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(id));

        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @GetMapping("")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        try {
            return ResponseEntity.ok(orderService.getAllOrders());
        } catch (OrderListEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Integer id, @RequestBody OrderDto orderDto) {
        try {
            return ResponseEntity.ok(orderService.updateOrder(id, orderDto));

        } catch (ConstraintViolationException e) {
            Map<String, String> errors = CheckControllerError.checkControllerErrors(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

        } catch (OrderDtoNullException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) {
        try {
            orderService.deleteOrder(id);

            return ResponseEntity.noContent().build();
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
