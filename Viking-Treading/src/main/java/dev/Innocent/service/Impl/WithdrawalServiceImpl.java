package dev.Innocent.service.Impl;

import dev.Innocent.enums.WithdrawalStatus;
import dev.Innocent.model.User;
import dev.Innocent.model.Withdrawal;
import dev.Innocent.repository.WithdrawalRepository;
import dev.Innocent.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WithdrawalServiceImpl implements WithdrawalService {
    private final WithdrawalRepository withdrawalRepository;

    @Override
    public Withdrawal requestWithdrawal(Long amount, User user) {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAmount(amount);
        withdrawal.setUser(user);
        withdrawal.setStatus(WithdrawalStatus.PENDING);
        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public Withdrawal proceedWithWithdrawal(Long withdrawalId, boolean approved) throws Exception {
        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(withdrawalId);
        if (optionalWithdrawal.isEmpty()) {
            throw new Exception("Withdrawal not found");
        }
        Withdrawal withdrawal = optionalWithdrawal.get();
        withdrawal.setDate(LocalDateTime.now());
        withdrawal.setStatus(approved ? WithdrawalStatus.APPROVED : WithdrawalStatus.REJECTED);
        return withdrawalRepository.save(withdrawal);
    }

    @Override
    public List<Withdrawal> getUsersWithdrawalsHistory(User user) {
        return withdrawalRepository.findByUserId(user.getId());
    }

    @Override
    public List<Withdrawal> getAllWithdrawalsRequest() {
        return withdrawalRepository.findAll();
    }
}
