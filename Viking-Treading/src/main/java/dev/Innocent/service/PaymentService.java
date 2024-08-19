package dev.Innocent.service;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import dev.Innocent.DTO.response.PaymentResponse;
import dev.Innocent.enums.PaymentMethod;
import dev.Innocent.model.PaymentOrder;
import dev.Innocent.model.User;

public interface PaymentService {
    PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);
    PaymentOrder getPaymentOrderById(Long id);
    Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException;
    PaymentResponse createRazorpayPaymentLink(User user, Long amount);
    PaymentResponse createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException;
}
