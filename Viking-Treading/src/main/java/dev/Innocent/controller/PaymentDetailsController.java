package dev.Innocent.controller;

import dev.Innocent.model.PaymentDetails;
import dev.Innocent.model.User;
import dev.Innocent.service.PaymentDetailsService;
import dev.Innocent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-details")
@RequiredArgsConstructor
public class PaymentDetailsController {
    private final PaymentDetailsService paymentDetailsService;
    private final UserService userService;

    @PostMapping("/payment-details")
    public ResponseEntity<PaymentDetails> addPaymentDetails(
            @RequestHeader("Authorization") String jwt,
            @RequestBody PaymentDetails paymentDetailsRequest) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        PaymentDetails paymentDetails = paymentDetailsService.addPaymentDetails(
                paymentDetailsRequest.getAccountNumber(),
                paymentDetailsRequest.getAccountHolderName(),
                paymentDetailsRequest.getIfsc(),
                paymentDetailsRequest.getBankName(),
                user
        );
        return ResponseEntity.ok(paymentDetails);
    }

    @GetMapping("/payment-details")
    public ResponseEntity<PaymentDetails> getPaymentDetailsByUser(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        PaymentDetails paymentDetails = paymentDetailsService.getPaymentDetailsByUser(user);
        return ResponseEntity.ok(paymentDetails);
    }
}
