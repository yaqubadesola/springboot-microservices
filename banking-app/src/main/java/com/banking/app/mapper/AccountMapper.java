package com.banking.app.mapper;

import com.banking.app.dto.AccountDto;
import com.banking.app.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountMapper {
    private final AccountDto accountDto;
    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
                );
        return account;

    }

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;

    }
}
