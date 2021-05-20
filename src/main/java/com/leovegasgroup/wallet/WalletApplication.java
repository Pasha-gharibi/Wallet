package com.leovegasgroup.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletApplication.class, args)
                .addApplicationListener(new ApplicationListener<ApplicationEvent>() {
                                            @Override
                                            public void onApplicationEvent(ApplicationEvent applicationEvent) {
                                                try {
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                        }

                );


    }

    @Bean(initMethod = "init")
    public InitialConfigurationService initCache() {
        return new InitialConfigurationService();
    }

}
