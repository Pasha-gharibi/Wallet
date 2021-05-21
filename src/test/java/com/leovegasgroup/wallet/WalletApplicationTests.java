package com.leovegasgroup.wallet;

import com.leovegasgroup.wallet.domain.enumeration.TransactionType;
import com.leovegasgroup.wallet.service.AccountService;
import com.leovegasgroup.wallet.service.TransactionService;
import com.leovegasgroup.wallet.service.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class WalletApplicationTests {

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @Test
    void contextLoads() {
//        for (int i = 6; i <1006; i++) {
//            TransactionDTO t = new TransactionDTO()
//                    .amount(BigDecimal.valueOf(Long.valueOf(i)))
//                    .player(Long.valueOf(i % 5))
////                    .type( (i % 2 == 2 ) ? TransactionType.Credit : TransactionType.Debit);
//                    .type(TransactionType.Credit);
//            transactionService.run(t);
//            System.out.println(accountService.balance(Long.valueOf(i % 5)).toString());
//        }


    }

}
