package com.banking.app.controller;

import com.banking.app.dto.AccountDto;
import com.banking.app.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> create(@RequestBody AccountDto accountDto){
        AccountDto newAccount = accountService.createAccount(accountDto);
        return  new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
        AccountDto account = accountService.getAccount(id);
        return  new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("{id}/deposit")
    public ResponseEntity<AccountDto> updateDeposit(@PathVariable Long id,@RequestBody Map<String, Double> request){
        Double balance = request.get("balance");
        AccountDto updatedAcc =  accountService.updateDeposit(id,balance);
        return  new ResponseEntity<>(updatedAcc, HttpStatus.OK);
    }


    @PutMapping("{id}/withdrawal")
    public ResponseEntity<AccountDto> withdrawal(@PathVariable Long id,@RequestBody Map<String, Double> request){
        Double balance = request.get("balance");
        AccountDto updatedAcc =  accountService.updateWithdrawal(id,balance);
        return  new ResponseEntity<>(updatedAcc, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        //return new ResponseEntity<>(users, HttpStatus.OK);
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        System.out.println("+++++Controller ID is here+++++");
        System.out.println(id);
        accountService.deleteAccount(id);
        //return new ResponseEntity<>(users, HttpStatus.OK);
        return ResponseEntity.ok("Accounts with id "+ id + " successfully deleted");
    }


}
