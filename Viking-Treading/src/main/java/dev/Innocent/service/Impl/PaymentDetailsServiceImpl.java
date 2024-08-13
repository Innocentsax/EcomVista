package dev.Innocent.service.Impl;

import dev.Innocent.model.PaymentDetails;
import dev.Innocent.model.User;
import dev.Innocent.repository.PaymentDetailsRepository;
import dev.Innocent.service.PaymentDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentDetailsServiceImpl implements PaymentDetailsService {
    private final PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public PaymentDetails addPaymentDetails(String accountNumber, String accountHolderName, String ifsc, String bankName, User user) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setAccountNumber(accountNumber);
        paymentDetails.setAccountHolderName(accountHolderName);
        paymentDetails.setIfsc(ifsc);
        paymentDetails.setBankName(bankName);
        paymentDetails.setUser(user);
        return paymentDetailsRepository.save(paymentDetails);
    }

    @Override
    public PaymentDetails getPaymentDetailsByUser(User user) {
        return paymentDetailsRepository.findByUserId(user.getId());
    }
}
