package dev.Innocent.service;

import dev.Innocent.model.User;
import dev.Innocent.model.Withdrawal;

import java.util.List;

public interface WithdrawalService {
    Withdrawal requestWithdrawal(Long amount, User user);
    Withdrawal proceedWithWithdrawal(Long withdrawalId, boolean approved) throws Exception;
    List<Withdrawal> getUsersWithdrawalsHistory(User user);
    List<Withdrawal> getAllWithdrawalsRequest();
}
