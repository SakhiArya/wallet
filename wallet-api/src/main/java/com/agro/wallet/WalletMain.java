package com.agro.wallet;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableScheduling
@ComponentScan({"com.agro"})
@EntityScan({"com.agro.wallet","com.agro.wallet.entities"})
public class WalletMain {

    public static void main(String[] args) {
        SpringApplication.run(WalletMain.class, args);
    }


}
