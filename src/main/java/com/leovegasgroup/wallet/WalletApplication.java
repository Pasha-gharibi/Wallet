package com.leovegasgroup.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletApplication.class, args);
    }

    @Bean(initMethod = "init")
    public InitialConfigurationService initCache() {
        return new InitialConfigurationService();
    }

}
