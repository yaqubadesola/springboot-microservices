package com.banking.app.service;

import com.banking.app.dto.AccountDto;

import java.util.List;


public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccount(Long id);
    AccountDto updateDeposit(Long id, Double balance);
    AccountDto updateWithdrawal(Long id, Double balance);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
}
