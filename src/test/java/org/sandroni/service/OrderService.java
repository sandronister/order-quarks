package org.sandroni.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.sandroni.client.CustomerClient;
import org.sandroni.client.ProductClient;
import org.sandroni.dto.CustomerDTO;
import org.sandroni.dto.OrderDTO;
import org.sandroni.dto.ProductDTO;
import org.sandroni.entity.OrderEntity;
import org.sandroni.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @RestClient
    @Inject
    CustomerClient customerClient;

    @RestClient
    @Inject
    ProductClient productService;

    public void createOrder(OrderDTO orderDTO) {
        this.validateOrder(orderDTO);
        orderRepository.persist(mapperToEntity(orderDTO));
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(this::mapperToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO findById(Long id) {
        OrderEntity order=  orderRepository.findById(id);
        return mapperToDTO(order);
    }

    private void validateCustomer(OrderDTO orderDTO) {
        CustomerDTO customerDTO = customerClient.getCustomer(orderDTO.getCustomerId());
        if (customerDTO == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        if (!customerDTO.getName().equals(orderDTO.getCustomerName())) {
            throw new IllegalArgumentException("Customer name does not match");
        }
    }

    private void validProduct(OrderDTO orderDTO) {
        ProductDTO productDTO = productService.getProduct(orderDTO.getProductId());
        if (productDTO == null) {
            throw new IllegalArgumentException("Product not found");
        }
    }

    private void validateOrder(OrderDTO orderDTO) {
        orderDTO.validateOrder();
        this.validateCustomer(orderDTO);
        this.validProduct(orderDTO);
    }

    private OrderEntity mapperToEntity(OrderDTO orderDTO) {
        return OrderEntity.builder()
                .customerId(orderDTO.getCustomerId())
                .customerName(orderDTO.getCustomerName())
                .productId(orderDTO.getProductId())
                .orderValue(orderDTO.getOrderValue())
                .build();
    }

    private OrderDTO mapperToDTO(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .customerId(orderEntity.getCustomerId())
                .customerName(orderEntity.getCustomerName())
                .productId(orderEntity.getProductId())
                .orderValue(orderEntity.getOrderValue())
                .build();
    }

}
