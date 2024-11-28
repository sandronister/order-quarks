package org.sandroni.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.sandroni.dto.OrderDTO;
import org.sandroni.service.OrderService;

import java.util.List;

@Path("api/v1/order")
public class OrderController {

    @Inject
    OrderService orderService;

    @POST
    @Transactional
    public Response createOrder(OrderDTO order) {
        try {
            orderService.createOrder(order);
            return Response.created(null).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    public List<OrderDTO> getOrders() {
        return orderService.findAll();
    }
}
