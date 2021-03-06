package com.itchain.msadddeventsourcing.orderservice.web.controller;

import com.itchain.msadddeventsourcing.orderservice.application.OrderApplicationServiceImpl;
import com.itchain.msadddeventsourcing.orderservice.application.OrderQueryService;
import com.itchain.msadddeventsourcing.orderservice.domain.Order;
import com.itchain.msadddeventsourcing.orderservice.domain.command.CreateOrderCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderApplicationServiceImpl tradeApplicationService;

    @Autowired
    OrderQueryService orderQueryService;

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public Order getTradeById(@PathVariable String id) {
        return orderQueryService.getOrderById(id);
    }


    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public Order createTrade(@RequestBody CreateOrderCommand createOrderCommand) {
        Order order = tradeApplicationService.addTrade(createOrderCommand.getProductId(), createOrderCommand.getBuyerId(), createOrderCommand.getSignedDate(), createOrderCommand.getPrice(), createOrderCommand.getQuantity());
        if (order == null) {
            return null;
        }

        return order;
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    public Order cancelTrade(@PathVariable String id) {
        Order order = tradeApplicationService.cancelTrade(id);
        return order;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getOrderListByCustomerId(@RequestParam("buyer") String buyerId) {
        return orderQueryService.getOrderListByBuyerId(buyerId);
    }
}
