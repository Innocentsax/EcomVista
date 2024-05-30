package dev.innocent.ecomvista.service;

import dev.innocent.ecomvista.DTO.request.CustomerRequest;
import dev.innocent.ecomvista.DTO.response.CustomerResponse;
import dev.innocent.ecomvista.customer.Customer;
import dev.innocent.ecomvista.exception.CustomerNotFoundException;
import dev.innocent.ecomvista.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer:: No customer " +
                                "found with the provided ID:: %s", request.id())));
        mergerCustomer(customer, request);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomer() {
        return customerRepository.findAll().stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("No customer found with the provided ID:: %s", customerId)));
        return mapper.fromCustomer(customer);
    }

    public void deleteById(String customerId) {
        if(!customerRepository.existsById(customerId)){
            throw new CustomerNotFoundException(
                    format("No customer found with the provided ID:: %s", customerId));
        }
        customerRepository.deleteById(customerId);
    }
}
