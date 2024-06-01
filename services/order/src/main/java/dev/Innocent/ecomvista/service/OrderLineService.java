package dev.Innocent.ecomvista.service;

import dev.Innocent.ecomvista.DTO.request.OrderLineRequest;
import dev.Innocent.ecomvista.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

}
