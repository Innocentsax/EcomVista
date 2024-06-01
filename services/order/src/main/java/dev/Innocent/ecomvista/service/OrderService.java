package dev.Innocent.ecomvista.service;

import dev.Innocent.ecomvista.DTO.request.OrderLineRequest;
import dev.Innocent.ecomvista.DTO.request.OrderRequest;
import dev.Innocent.ecomvista.DTO.request.PurchaseRequest;
import dev.Innocent.ecomvista.connect.CustomerClient;
import dev.Innocent.ecomvista.connect.ProductClient;
import dev.Innocent.ecomvista.exception.BusinessException;
import dev.Innocent.ecomvista.model.OrderLine;
import dev.Innocent.ecomvista.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest orderRequest) {
        // Check the customer --> call customer service using feign client
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: Customer not found with id " + orderRequest.customerId());

        // Purchase the product --> call product service using RestTemplate
        this.productClient.purchaseProduct(orderRequest.products());

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

        // todo start Payment process

        // send the order confirmation -> notification service (kafta)
    }
}
