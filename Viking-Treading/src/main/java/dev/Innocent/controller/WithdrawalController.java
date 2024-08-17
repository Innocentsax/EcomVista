package dev.Innocent.controller;

import dev.Innocent.model.User;
import dev.Innocent.model.Wallet;
import dev.Innocent.model.Withdrawal;
import dev.Innocent.service.UserService;
import dev.Innocent.service.WalletService;
import dev.Innocent.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class WithdrawalController {
    private final WithdrawalService withdrawalService;
    private final UserService userService;
    private final WalletService walletService;


    @PostMapping("/api/withdrawal/{amount}")
    public ResponseEntity<?> withdrawalRequest(
            @RequestHeader("Authorization") String jwt, @PathVariable Long amount) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.getUserWallet(user);

        Withdrawal withdrawal = withdrawalService.requestWithdrawal(amount, user);
        walletService.addWallet(wallet, -withdrawal.getAmount());
        return new ResponseEntity<>(withdrawal, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/api/admin/withdrawal/{withdrawalId}/proceed/{approved}")
    public ResponseEntity<Withdrawal> proceedWithWithdrawal(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long withdrawalId,
            @PathVariable boolean approved) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Withdrawal withdrawal = withdrawalService.proceedWithWithdrawal(withdrawalId, approved);
        Wallet wallet = walletService.getUserWallet(user);
        if (!approved){
            walletService.addWallet(wallet, withdrawal.getAmount());
        }
        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping("/api/withdrawal")
    public ResponseEntity<?> getUsersWithdrawalsHistory(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Withdrawal> withdrawals = withdrawalService.getUsersWithdrawalsHistory(user);
        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }

    @GetMapping("/api/admin/withdrawal")
    public ResponseEntity<?> getAllWithdrawalsRequest(
            @RequestHeader("Authorization") String jwt) throws Exception {
        List<Withdrawal> withdrawals = withdrawalService.getAllWithdrawalsRequest();
        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }

}
