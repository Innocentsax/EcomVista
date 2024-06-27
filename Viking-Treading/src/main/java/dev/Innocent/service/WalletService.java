package dev.Innocent.service;

import dev.Innocent.model.Order;
import dev.Innocent.model.User;
import dev.Innocent.model.Wallet;

public interface WalletService {
    Wallet getUserWallet(User user) throws Exception;
    Wallet addWallet(Wallet wallet, Long money) throws Exception;
    Wallet findWalletById(Long id) throws Exception;
    Wallet walletToWalletTransfer(User sender, Wallet receiver, Long amount) throws Exception;
    Wallet payOrderPayment(Order order, User user) throws Exception;
}
