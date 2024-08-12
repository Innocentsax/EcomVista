package dev.Innocent.service;

import dev.Innocent.model.PaymentDetails;
import dev.Innocent.model.User;

public interface PaymentDetailsService {
    PaymentDetails addPaymentDetails(String accountNumber, String accountHolderName,
                                     String ifsc, String bankName, User user);
    PaymentDetails getPaymentDetailsByUser(User user);
}
