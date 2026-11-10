package com.banking.app.service;

import com.banking.app.dto.AccountDto;
import com.banking.app.entity.Account;
import com.banking.app.mapper.AccountMapper;
import com.banking.app.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account newAccount = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(newAccount);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccount(Long id) {
        Account retrievedAccount = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account with id: "+id + "does not exist"));
        return AccountMapper.mapToAccountDto(retrievedAccount);
    }

    @Override
    public AccountDto updateWithdrawal(Long id, Double balance) {
        Account retrievedAccount = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account with id: "+id + "does not exist"));
        Double total = retrievedAccount.getBalance() - balance;
        retrievedAccount.setBalance(total);
        Account updatedAcc = accountRepository.save(retrievedAccount);
        return AccountMapper.mapToAccountDto(updatedAcc);
    }

    @Override
    public AccountDto updateDeposit(Long id, Double balance) {
        Account retrievedAccount = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account with id: "+id + "does not exist"));
        Double total = retrievedAccount.getBalance() + balance;
        retrievedAccount.setBalance(total);
        Account updatedAcc = accountRepository.save(retrievedAccount);
        return AccountMapper.mapToAccountDto(updatedAcc);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> allAccounts = accounts.stream().map(account -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
        return allAccounts;
    }

    @Override
    public void deleteAccount(Long id) {
        System.out.println("+++++ID is here+++++");
        System.out.println(id);
        Account retrievedAccount = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account with id: "+id + "does not exist"));
        accountRepository.deleteById(id);
    }

}
