package dev.Innocent.controller;

import dev.Innocent.model.*;
import dev.Innocent.service.OrderService;
import dev.Innocent.service.PaymentService;
import dev.Innocent.service.UserService;
import dev.Innocent.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final UserService userService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    @GetMapping("/api/wallet")
    public ResponseEntity<Wallet> getUserWallet(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.getUserWallet(user);
        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/wallet/{walletId}/transfer")
    public ResponseEntity<Wallet> walletToWalletTransfer(
            @RequestHeader("Authorization") String jwt,
            @PathVariable("walletId") Long walletId,
            @RequestBody WalletTransaction req) throws Exception {
        User senderUser = userService.findUserProfileByJwt(jwt);
        Wallet receiverWallet = walletService.findWalletById(walletId);
        Wallet senderWallet = walletService.walletToWalletTransfer(senderUser, receiverWallet, req.getAmount());
        return new ResponseEntity<>(senderWallet, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/wallet/order/{orderId}/pay")
    public ResponseEntity<Wallet> payOrderPayment(
            @RequestHeader("Authorization") String jwt,
            @PathVariable("orderId") Long orderId) throws Exception{
        User senderUser = userService.findUserProfileByJwt(jwt);
        Order order = orderService.getOrderById(orderId);
        Wallet senderWallet = walletService.payOrderPayment(order, senderUser);
        return new ResponseEntity<>(senderWallet, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/wallet/deposit")
    public ResponseEntity<Wallet> addBalanceToWallet(
            @RequestHeader("Authorization") String jwt,
            @RequestParam(name = "order_id") Long orderId,
            @RequestParam(name = "payment_id") String paymentId) throws Exception{
        User senderUser = userService.findUserProfileByJwt(jwt);
        Wallet senderWallet = walletService.getUserWallet(senderUser);

        PaymentOrder paymentOrder = paymentService.getPaymentOrderById(orderId);
        Boolean status = paymentService.proceedPaymentOrder(paymentOrder, paymentId);
        if(status){
            senderWallet = walletService.addWallet(senderWallet, paymentOrder.getAmount());
        }

        return new ResponseEntity<>(senderWallet, HttpStatus.ACCEPTED);
    }
}
