package dev.Innocent.ecomvista.service;

import dev.Innocent.ecomvista.DTO.request.OrderLineRequest;
import dev.Innocent.ecomvista.DTO.request.OrderRequest;
import dev.Innocent.ecomvista.DTO.request.PaymentRequest;
import dev.Innocent.ecomvista.DTO.request.PurchaseRequest;
import dev.Innocent.ecomvista.DTO.response.OrderConfirmation;
import dev.Innocent.ecomvista.DTO.response.OrderResponse;
import dev.Innocent.ecomvista.config.KafkaOrderProducer;
import dev.Innocent.ecomvista.connect.CustomerClient;
import dev.Innocent.ecomvista.connect.PaymentClient;
import dev.Innocent.ecomvista.connect.ProductClient;
import dev.Innocent.ecomvista.exception.BusinessException;
import dev.Innocent.ecomvista.model.OrderLine;
import dev.Innocent.ecomvista.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final KafkaOrderProducer kafkaOrderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {
        // Check the customer --> call customer service using feign client
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: Customer not found with id " + orderRequest.customerId()));

        // Purchase the product --> call product service using RestTemplate
        var purchaseProducts = this.productClient.purchaseProduct(orderRequest.products());

        // Save the order or persist the order
        var order = this.orderRepository.save(mapper.toOrder(orderRequest));

        // Persist the order line
        for(PurchaseRequest purchaseRequest : orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //start Payment process
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation -> notification service (kafta)
        kafkaOrderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No Order found with the provided ID %d", orderId)));
    }
}
