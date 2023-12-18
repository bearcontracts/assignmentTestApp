package com.example.demoapp.Service;

import com.example.demoapp.DTO.Request.DepositRequest;
import com.example.demoapp.DTO.Request.RefundRequest;
import com.example.demoapp.Model.Deposit;
import com.example.demoapp.Model.User;
import com.example.demoapp.Repository.DepositRepository;
import com.example.demoapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepositService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Transactional
    public User updateBalance(DepositRequest request) {
        String username = request.getUsername();
        int amount = request.getAmount();

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Integer balance = user.getBalance();
            int currentBalance = (balance != null) ? balance : 0;
            int updatedBalance = currentBalance + amount;
            user.setBalance(updatedBalance);

            String depositId = UUID.randomUUID().toString();
            Deposit deposit = new Deposit();
            deposit.setAmount(amount);
            deposit.setDepositId(depositId);
            deposit.setUser(user);
            user.setDepositId(depositId);

            user.getDeposits().add(deposit);
            depositRepository.save(deposit);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Transactional
    public User refundBalance(RefundRequest request) {
        String depositId = request.getDepositId();

        Optional<Deposit> deposits = depositRepository.findDepositByDepositId(depositId);

        if (deposits.isPresent()) {
            Deposit deposit = deposits.get();
            User user = deposit.getUser();

            int depAmount = deposit.getAmount();
            int currBalance = user.getBalance();

            int updatedBalance = currBalance - depAmount;

            user.setBalance(updatedBalance);
            userRepository.save(user);

            depositRepository.delete(deposit);
            return user;
        }
        return null;
    }

}
